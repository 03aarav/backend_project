//package com.example.demo.Repository;
//
//
//
//import com.example.demo.Model.UserVerification; import org.springframework.data.mongodb.repository.MongoRepository;
//
//import java.util.Optional;
//
//public interface UserVerificationRepository extends MongoRepository<UserVerification, String> {
//    Optional<UserVerification> findByToken(String token);
//
//    Optional<UserVerification> findByUserId(String userId);
//
//    boolean existsByUserId(String userId);
//
//}