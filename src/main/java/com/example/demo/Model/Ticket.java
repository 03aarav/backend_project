package com.example.demo.Model;




import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    private String ticketId;
    private double cost;
    private int age;
    private String nationality;
    private boolean isStudent;
    private String qrCodeData;
    private String ticketType;
}

