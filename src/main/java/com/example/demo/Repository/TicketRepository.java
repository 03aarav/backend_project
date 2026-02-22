package com.example.demo.Repository;


import com.example.demo.Model.Ticket;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TicketRepository extends MongoRepository<Ticket, String> {

    // Find all tickets by nationality (e.g., Indian, Foreigner)
    List<Ticket> findByNationality(String nationality);

    // Find all student tickets
    List<Ticket> findByIsStudentTrue();

    // Find by ticket type (e.g., STUDENT, ADULT)
    List<Ticket> findByTicketType(String ticketType);
}
