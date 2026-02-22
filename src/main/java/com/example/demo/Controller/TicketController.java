package com.example.demo.Controller;

import com.example.demo.Model.Ticket;
import com.example.demo.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    // Create Ticket
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket) {
        return ticketService.createTicket(ticket);
    }

    // Get Ticket by ID
    @GetMapping("/{id}")
    public Optional<Ticket> getTicketById(@PathVariable String id) {
        return ticketService.getTicketById(id);
    }

    // Get All Tickets
    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    // Update Ticket
    @PutMapping("/{id}")
    public Ticket updateTicket(@PathVariable String id, @RequestBody Ticket ticket) {
        return ticketService.updateTicket(id, ticket);
    }

    // Delete Ticket
    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable String id) {
        ticketService.deleteTicket(id);
    }

    // Get Tickets by Nationality
    @GetMapping("/nationality/{nationality}")
    public List<Ticket> getTicketsByNationality(@PathVariable String nationality) {
        return ticketService.getTicketsByNationality(nationality);
    }

    // Get All Student Tickets
    @GetMapping("/students")
    public List<Ticket> getStudentTickets() {
        return ticketService.getStudentTickets();
    }
}
