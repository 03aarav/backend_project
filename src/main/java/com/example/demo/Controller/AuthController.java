package com.example.demo.Controller;

import com.example.demo.DTO.OtpRequest;
import com.example.demo.DTO.ResetPassword;
import com.example.demo.Model.PasswordResetOtp;
import com.example.demo.Model.User;
import com.example.demo.Repository.OtpRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.OtpService;
import com.example.demo.Util.JwtUtil;
import com.example.demo.Util.OtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired UserRepository repo;
    @Autowired PasswordEncoder encoder;
    @Autowired JwtUtil jwt;
    @Autowired
    private OtpService otpService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private OtpUtil otpUtil;

    @PostMapping("/register")
    public String register(@RequestBody User user){

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");

        repo.save(user);
        return "Registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User request){



        User user = repo.findByUserName(request.getUserName()).orElseThrow();
        if(encoder.matches(request.getPassword(), user.getPassword())){
            return jwt.generateToken(user.getUserName());
        }
       throw new RuntimeException("Invalid Credentials");
    }

    @GetMapping
    public String test(){
        return "succes";
    }


    @PostMapping("/forgot-password")
    public String forgot(@RequestBody OtpRequest req){
        otpService.sendOtp(req.getEmail());
        return "sent";
    }

    @PostMapping("/verify-otp")
    public String verify(@RequestBody OtpRequest req){
       return otpService.verifyOtp(req.getEmail(), req.getOtp());

    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody ResetPassword req){
        otpService.resetPassword(req.getEmail(),req.getToken(),req.getNewPassword());
        return "Password Reset Successfully";
    }










}

