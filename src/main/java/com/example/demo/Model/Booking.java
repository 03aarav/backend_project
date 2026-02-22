package com.example.demo.Model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    private String transactionId;
    private String userId;
    private String travelerId;
    private String museumId;
    private int noOfTravelers;
    private double totalCost;
    private String modeOfBooking;
    private boolean checkedIn;
    private String paymentStatus;
    private LocalDate date;
    private LocalTime time;
}

