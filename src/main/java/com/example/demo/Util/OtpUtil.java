package com.example.demo.Util;

import jdk.jfr.Category;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

import java.util.UUID;

import java.security.SecureRandom;

@Component
public class OtpUtil {

    private static final SecureRandom random = new SecureRandom();

    public static String generateOtp() {
        return String.valueOf(100000 + random.nextInt(900000));
    }

    public static String hashOtp(String otp) {
        return BCrypt.hashpw(otp, BCrypt.gensalt());
    }

    public static boolean verifyOtp(String rawOtp, String hashedOtp) {
        return BCrypt.checkpw(rawOtp, hashedOtp);
    }

    public static String generateRefreshToken() {
        return UUID.randomUUID().toString();
    }



}