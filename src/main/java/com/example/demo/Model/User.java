package com.example.demo.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    private String id;

    private String userName;
    private String email;

    private String password;   // renamed

    private String phoneNo;
    private LocalDate dateOfBirth;

    private String role; // ROLE_USER / ROLE_ADMIN

    private String language;
    private String nationality;
    private List<String> interests;

    private Address address;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Address {
        private String city;
        private String state;
        private String country;
        private String pinCode;
    }
}
