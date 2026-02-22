package com.example.demo.Service;



import com.example.demo.Model.Ticket;
import java.util.List;
import java.util.Optional;

public interface TicketService {
    Ticket createTicket(Ticket ticket);
    Optional<Ticket> getTicketById(String id);
    List<Ticket> getAllTickets();
    Ticket updateTicket(String id, Ticket ticket);
    void deleteTicket(String id);

    List<Ticket> getTicketsByNationality(String nationality);

    List<Ticket> getStudentTickets();
}