package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "password_reset_otps")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PasswordResetOtp {

    @Id
    private String id;

    /**
     * User email requesting password reset
     */
    @Indexed
    private String email;

    /**
     * Hashed OTP (never store raw OTP)
     */
    private String otpHash;

    /**
     * Whether OTP has been used
     */
    private boolean otpUsed;

    /**
     * Hashed reset token (generated after OTP verification)
     */
    private String resetTokenHash;

    /**
     * Expiry time of reset token
     */
    private Date resetTokenExpiry;

    /**
     * Creation time of OTP record
     * TTL index auto deletes document after 5 minutes
     */
    @Indexed(expireAfterSeconds = 300)
    private Date createdAt;

    /**
     * Number of failed attempts (for rate limiting)
     */
    private int attemptCount;

    /**
     * Constructor default values
     */
    public static PasswordResetOtp create(String email, String otpHash) {
        return PasswordResetOtp.builder()
                .email(email)
                .otpHash(otpHash)
                .otpUsed(false)
                .attemptCount(0)
                .createdAt(new Date())
                .build();
    }
}