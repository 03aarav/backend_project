package com.example.demo.Model;




import lombok.*;
        import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "travelers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Traveler {
    @Id
    private String travelerId;
    private String name;
    private LocalDate dateOfBirth;
    private String gender;
    private String nationality;
    private String phoneNo; // optional
}
