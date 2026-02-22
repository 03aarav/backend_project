package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    private String id;

    private String bookingId;
    private double amount;
    private String paymentMethod; // e.g., card, UPI, cash
    private String paymentStatus; // e.g., success, failed
    private LocalDateTime paymentDateTime;
    private String qrCodeUrl; // URL or base64 of generated QR code
}
