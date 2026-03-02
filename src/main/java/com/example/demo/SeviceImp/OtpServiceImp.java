package com.example.demo.SeviceImp;

import com.example.demo.Model.PasswordResetOtp;
import com.example.demo.Model.User;
import com.example.demo.Repository.OtpRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.OtpService;
import com.example.demo.Util.OtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OtpServiceImp implements OtpService {

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void sendOtp(String email) {

        // delete all old records for safety
        otpRepository.deleteByEmail(email);

        String otp = OtpUtil.generateOtp();
        String hashedOtp = OtpUtil.hashOtp(otp);

        PasswordResetOtp record = PasswordResetOtp.create(email, hashedOtp);

        otpRepository.save(record);

        sendEmail(email, otp);
    }

    @Override
    public String verifyOtp(String email, String otp) {

        PasswordResetOtp record = otpRepository
                .findByEmail(email)
                .orElseThrow(() -> new RuntimeException("OTP not found. Please request new OTP."));

        if (record.isOtpUsed()) {
            throw new RuntimeException("OTP already used.");
        }

        // Manual expiry check (5 minutes)

        long fiveMinutesAgo = System.currentTimeMillis() - (5 * 60 * 1000);

        if (record.getCreatedAt().getTime() < fiveMinutesAgo) {
            otpRepository.delete(record);
            throw new RuntimeException("OTP expired");
        }

        // Brute force protection
        if (!OtpUtil.verifyOtp(otp, record.getOtpHash())) {

            record.setAttemptCount(record.getAttemptCount() + 1);

            if (record.getAttemptCount() >= 5) {
                otpRepository.delete(record);
                throw new RuntimeException("Too many attempts. Request new OTP.");
            }

            otpRepository.save(record);
            throw new RuntimeException("Invalid2 OTP");
        }

        // Mark OTP as used
        record.setOtpUsed(true);

        // Generate reset token
        String resetToken = OtpUtil.generateRefreshToken();

        String hashedToken = passwordEncoder.encode(resetToken);

        record.setResetTokenHash(hashedToken);

        record.setResetTokenExpiry(
                new Date(System.currentTimeMillis() + 10 * 60 * 1000)
        );

        otpRepository.save(record);

        return resetToken;
    }



    @Override
    public void resetPassword(String email, String resetToken, String newPassword) {

        PasswordResetOtp record = otpRepository
                .findByEmailAndOtpUsedTrue(email)
                .orElseThrow(() -> new RuntimeException("Invalid request"));

        if (record.getResetTokenExpiry() == null ||
                record.getResetTokenExpiry().before(new Date())) {
            throw new RuntimeException("Token expired");
        }

        if (!passwordEncoder.matches(resetToken, record.getResetTokenHash())) {
            throw new RuntimeException("Invalid token");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        otpRepository.delete(record); // one-time use
    }



    private void sendEmail(String to, String otp) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Password Reset OTP");
        message.setText("Your OTP is: " + otp + "\nValid for 5 minutes.");
        mailSender.send(message);
    }
}
