package com.example.demo.Controller;

import com.example.demo.Model.Traveler;
import com.example.demo.Service.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/travelers")
@CrossOrigin
@RequiredArgsConstructor
public class TravellerController {

    private final TravelerService travelerService;

    // Create new traveler
    @PostMapping
    public Traveler createTraveler(@RequestBody Traveler traveler) {
        return travelerService.createTraveler(traveler);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Traveler> getTravelerById(@PathVariable String id) {
        return travelerService.getTravelerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Get all travelers
    @GetMapping
    public List<Traveler> getAllTravelers() {
        return travelerService.getAllTravelers();
    }

    // Update traveler
    @PutMapping("/{id}")
    public Traveler updateTraveler(@PathVariable String id, @RequestBody Traveler traveler) {
        return travelerService.updateTraveler(id, traveler);
    }

    // Delete traveler
    @DeleteMapping("/{id}")
    public void deleteTraveler(@PathVariable String id) {
        travelerService.deleteTraveler(id);
    }
}
