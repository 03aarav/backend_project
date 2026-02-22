//package com.example.demo.Controller;
//
//
//import com.example.demo.Model.UserVerification; import com.example.demo.Service.UserVerificationService; import lombok.RequiredArgsConstructor; import org.springframework.http.ResponseEntity; import org.springframework.web.bind.annotation.*;
//
//        import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/verify")
//@CrossOrigin
//@RequiredArgsConstructor
//public class UserVerificationController {
//    private final UserVerificationService userVerificationService;
//
//    // Endpoint to create a verification token for a user (e.g., after registration)
//    @PostMapping("/generate/{userId}")
//    public ResponseEntity<UserVerification> generateToken(@PathVariable String userId) {
//        UserVerification verification = userVerificationService.createVerificationToken(userId);
//        return ResponseEntity.ok(verification);
//    }
//
//    // Endpoint to verify user using the token
//    @GetMapping("/{token}")
//    public ResponseEntity<String> verifyUser(@PathVariable String token) {
//        boolean verified = userVerificationService.verifyUser(token);
//
//        if (verified) {
//            return ResponseEntity.ok("User successfully verified.");
//        } else {
//            return ResponseEntity.badRequest().body("Verification failed or token expired.");
//        }
//    }
//
//    // Optional: Endpoint to fetch verification by token
//    @GetMapping("/token/{token}")
//    public ResponseEntity<UserVerification> getVerification(@PathVariable String token) {
//        Optional<UserVerification> optional = userVerificationService.getVerificationByToken(token);
//        return optional.map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
//}