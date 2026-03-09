package com.example.demo.Repository;


import com.example.demo.Model.Museum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MuseumRepository extends MongoRepository<Museum, String> {

    @Query("""
    {
      $or:[
        {name: {$regex:?0,$options:'i'}},
        {address: {$regex:?0,$options:'i'}},
        {state: {$regex:?0,$options:'i'}},
        {theme: {$regex:?0,$options:'i'}},
        {overview: {$regex:?0,$options:'i'}}
      ]
    }
    """)
    Page<Museum> search(String keyword, Pageable pageable);
}