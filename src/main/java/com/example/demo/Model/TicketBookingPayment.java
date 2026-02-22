package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "ticket_payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketBookingPayment {

    @Id
    private String id;

    private String userId;
    private String museumId;

    private int ticketCount;
    private double totalAmount;

    private String status; // PREVIEW / PAID

    private String qrCode;

    private Boolean used;  // NEW FIELD

    private LocalDateTime createdAt;
}