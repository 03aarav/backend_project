package com.example.demo.SeviceImp;

import com.example.demo.Model.Museum;
import com.example.demo.Repository.MuseumRepository;
import com.example.demo.Service.MuseumService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MuseumServiceImpl implements MuseumService {

    private final MuseumRepository museumRepository;

    @Override
    public Museum createMuseum(Museum museum) {
        return museumRepository.save(museum);
    }

    @Override
    public Optional<Museum> getMuseumById(String id) {
        return museumRepository.findById(id);
    }

    @Override
    public List<Museum> getAllMuseums() {
        return museumRepository.findAll();
    }

    @Override
    public Museum updateMuseum(String id, Museum updatedMuseum) {
        return museumRepository.findById(id)
                .map(existing -> {

                    existing.setName(updatedMuseum.getName());
                    existing.setAddress(updatedMuseum.getAddress());
                    existing.setState(updatedMuseum.getState());
                    existing.setOverview(updatedMuseum.getOverview());
                    existing.setTheme(updatedMuseum.getTheme());
                    existing.setEstablishment_year(updatedMuseum.getEstablishment_year());
                    existing.setAdmission_fee(updatedMuseum.getAdmission_fee());
                    existing.setTimings(updatedMuseum.getTimings());
                    existing.setFacilities(updatedMuseum.getFacilities());
                    existing.setContact_info(updatedMuseum.getContact_info());

                    return museumRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Museum not found with id: " + id));
    }

    @Override
    public void deleteMuseum(String id) {
        museumRepository.deleteById(id);
    }
}
