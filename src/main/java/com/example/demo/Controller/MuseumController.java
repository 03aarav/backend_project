package com.example.demo.Controller;
import com.example.demo.Model.Museum;
import com.example.demo.Service.MuseumService;
import lombok.RequiredArgsConstructor; import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/museums")
@CrossOrigin
@RequiredArgsConstructor
public class MuseumController {

    private final MuseumService museumService;

    // Create new museum
    @PostMapping
    public Museum createMuseum(@RequestBody Museum museum) {

        return museumService.createMuseum(museum);
    }

    // Get museum by ID
    @GetMapping("/{id}")
    public Museum getMuseumById(@PathVariable String id) {
        return museumService.getMuseumById(id)
                .orElseThrow(() -> new RuntimeException("Museum not found with id: " + id));
    }

    // Get all museums
    @GetMapping
    public List<Museum> getAllMuseums() {
        return museumService.getAllMuseums();
    }

    // Update museum
    @PutMapping("/{id}")
    public Museum updateMuseum(@PathVariable String id, @RequestBody Museum museum) {
        return museumService.updateMuseum(id, museum);
    }

    // Delete museum
    @DeleteMapping("/{id}")
    public void deleteMuseum(@PathVariable String id) {
        museumService.deleteMuseum(id);
    }

}
