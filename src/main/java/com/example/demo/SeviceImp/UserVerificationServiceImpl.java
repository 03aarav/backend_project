//package com.example.demo.SeviceImp;
//
//
//import com.example.demo.Model.User; import com.example.demo.Model.UserVerification; import com.example.demo.Repository.UserRepository; import com.example.demo.Repository.UserVerificationRepository; import com.example.demo.Service.UserVerificationService; import lombok.RequiredArgsConstructor; import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime; import java.util.Optional; import java.util.UUID;
//
//@Service @RequiredArgsConstructor public class UserVerificationServiceImpl implements UserVerificationService {
//    private final UserVerificationRepository verificationRepository;
//    private final UserRepository userRepository;
//
//    @Override
//    public UserVerification createVerificationToken(String userId) {
//        String token = UUID.randomUUID().toString();
//        UserVerification verification = UserVerification.builder()
//                .userId(userId)
//                .token(token)
//                .createdAt(LocalDateTime.now())
//                .expiresAt(LocalDateTime.now().plusHours(24)) // Token valid for 24 hours
//                .verified(false)
//                .build();
//
//        return verificationRepository.save(verification);
//    }
//
//    @Override
//    public Optional<UserVerification> getVerificationByToken(String token) {
//        return verificationRepository.findByToken(token);
//    }
//
//    @Override
//    public boolean verifyUser(String token) {
//        Optional<UserVerification> optionalVerification = verificationRepository.findByToken(token);
//
//        if (optionalVerification.isEmpty()) {
//            return false;
//        }
//
//        UserVerification verification = optionalVerification.get();
//
//        if (verification.getExpiresAt().isBefore(LocalDateTime.now())) {
//            return false; // Token expired
//        }
//
//        if (verification.isVerified()) {
//            return true; // Already verified
//        }
//
//        verification.setVerified(true);
//        verificationRepository.save(verification);
//
//        // Optional: Update User Entity to mark as verified (if applicable)
//        Optional<User> optionalUser = userRepository.findById(verification.getUserId());
//        optionalUser.ifPresent(user -> {
//            // You may want to add a field like 'isVerified' in User entity to set here
//            userRepository.save(user);
//        });
//
//        return true;
//    }
//}