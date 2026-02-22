package com.example.demo.Repository;



import com.example.demo.Model.Museum;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MuseumRepository extends MongoRepository<Museum, String> {
}
