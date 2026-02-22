package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "dataset")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Museum {

    @Id
    private String id;

    private String name;
    private String address;
    private String state;

    private String overview;
    private String theme;

    private String establishment_year;

    private String admission_fee;

    private String timings;

    private String facilities;

    private String contact_info;
}
