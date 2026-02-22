package com.example.demo.Service;



import com.example.demo.Model.Museum;
import java.util.List;
import java.util.Optional;

public interface MuseumService {
    Museum createMuseum(Museum museum);
    Optional<Museum> getMuseumById(String id);
    List<Museum> getAllMuseums();
    Museum updateMuseum(String id, Museum museum);
    void deleteMuseum(String id);
}
