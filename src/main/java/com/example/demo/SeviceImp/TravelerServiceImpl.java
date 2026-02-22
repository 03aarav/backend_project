package com.example.demo.SeviceImp;


import com.example.demo.Model.Traveler;
import com.example.demo.Repository.TravelerRepository;
import com.example.demo.Service.TravelerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelerServiceImpl implements TravelerService {

    private final TravelerRepository travelerRepository;

    @Override
    public Traveler createTraveler(Traveler traveler) {
        return travelerRepository.save(traveler);
    }

    @Override
    public Optional<Traveler> getTravelerById(String id) {
        return travelerRepository.findById(id);
    }

    @Override
    public List<Traveler> getAllTravelers() {
        return travelerRepository.findAll();
    }

    @Override
    public Traveler updateTraveler(String id, Traveler updatedTraveler) {
        return travelerRepository.findById(id)
                .map(existing -> {
                    existing.setName(updatedTraveler.getName());
                    existing.setGender(updatedTraveler.getGender());
                    existing.setDateOfBirth(updatedTraveler.getDateOfBirth());
                    existing.setPhoneNo(updatedTraveler.getPhoneNo());
                    existing.setNationality(updatedTraveler.getNationality());
                    return travelerRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Traveler not found with id: " + id));
    }

    @Override
    public void deleteTraveler(String id) {
        travelerRepository.deleteById(id);
    }
}

