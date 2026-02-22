
package com.example.demo.Model;

import lombok.*; import org.springframework.data.annotation.Id; import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "user_verifications")
@Data @AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserVerification {
    @Id
    private String id;

    private String userId;         // Reference to the User entity
    private String token;          // Verification token (random string or UUID)
    private LocalDateTime expiryDate;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;// Expiry time for token
    private boolean verified;      // Whether the user has verified or not

}