package com.example.demo.Repository;



import com.example.demo.Model.Traveler;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TravelerRepository extends MongoRepository<Traveler, String> {
}
