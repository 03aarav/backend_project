
// package: com.example.demo.Model

package com.example.demo.Model;

import lombok.*;
 import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("refresh_tokens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshToken {

    @Id
    private String id;
    private String userId;
    private String token;
    private LocalDateTime expiryDate;
}

