package com.example.demo.SeviceImp;

import com.example.demo.Model.Ticket;
import com.example.demo.Repository.TicketRepository;
import com.example.demo.Service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    @Override
    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    @Override
    public Optional<Ticket> getTicketById(String id) {
        return ticketRepository.findById(id);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket updateTicket(String id, Ticket updatedTicket) {
        Ticket existing = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found with ID: " + id));

        existing.setAge(updatedTicket.getAge());
        existing.setCost(updatedTicket.getCost());
        existing.setNationality(updatedTicket.getNationality());
        existing.setStudent(updatedTicket.isStudent());

        return ticketRepository.save(existing);
    }

    @Override
    public void deleteTicket(String id) {
        ticketRepository.deleteById(id);
    }

    @Override
    public List<Ticket> getTicketsByNationality(String nationality) {
        return ticketRepository.findAll()
                .stream()
                .filter(ticket -> nationality.equalsIgnoreCase(ticket.getNationality()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getStudentTickets() {
        return ticketRepository.findAll()
                .stream()
                .filter(Ticket::isStudent)
                .collect(Collectors.toList());
    }
}
