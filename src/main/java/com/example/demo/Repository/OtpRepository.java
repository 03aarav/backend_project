package com.example.demo.Repository;

import com.example.demo.Model.PasswordResetOtp;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpRepository extends MongoRepository<PasswordResetOtp, String> {

    Optional<PasswordResetOtp> findByEmailAndOtpUsedFalse(String email);

    Optional<PasswordResetOtp> findByEmailAndOtpUsedTrue(String email);

    Optional<PasswordResetOtp> findByEmail(String email);

    void deleteByEmail(String email);
}