package com.example.demo.Service;

public interface OtpService {

    void sendOtp(String email);

    String verifyOtp(String email, String otp);

     void resetPassword(String email, String resetToken, String newPassword);

}