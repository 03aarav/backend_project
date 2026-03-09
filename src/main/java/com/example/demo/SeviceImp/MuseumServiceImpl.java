package com.example.demo.SeviceImp;

import com.example.demo.Model.Museum;
import com.example.demo.Repository.MuseumRepository;
import com.example.demo.Service.MuseumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.MongoTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MuseumServiceImpl implements MuseumService {

    private final MuseumRepository museumRepository;
    private final MongoTemplate mongoTemplate;

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

    @Override
    public Page<Museum> searchMuseums(String keyword, int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        String[] tokens = keyword.split(" ");

        List<Criteria> criteriaList = new ArrayList<>();

        for (String token : tokens) {

            Criteria criteria = new Criteria().orOperator(
                    Criteria.where("name").regex(token, "i"),
                    Criteria.where("address").regex(token, "i"),
                    Criteria.where("state").regex(token, "i"),
                    Criteria.where("theme").regex(token, "i"),
                    Criteria.where("overview").regex(token, "i")
            );

            criteriaList.add(criteria);
        }

        Query query = new Query().addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));

        query.with(pageable);

        List<Museum> museums = mongoTemplate.find(query, Museum.class);

        long count = mongoTemplate.count(query.skip(0).limit(0), Museum.class);

        return new PageImpl<>(museums, pageable, count);
    }
}