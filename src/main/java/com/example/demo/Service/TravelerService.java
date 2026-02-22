package com.example.demo.Service;




import com.example.demo.Model.Traveler;
import java.util.List;
import java.util.Optional;

public interface TravelerService {
    Traveler createTraveler(Traveler traveler);
    Optional<Traveler> getTravelerById(String id);
    List<Traveler> getAllTravelers();
    Traveler updateTraveler(String id, Traveler traveler);
    void deleteTraveler(String id);
}
