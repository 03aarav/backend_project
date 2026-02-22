package com.example.demo.Controller;

import com.example.demo.Model.TicketBookingPayment;
import com.example.demo.Service.TicketBookingPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ticket-payment")
@RequiredArgsConstructor
@CrossOrigin
public class TicketBookingPaymentController {

    private final TicketBookingPaymentService service;

    // STEP 1 - Preview
    @PostMapping("/preview")
    public TicketBookingPayment preview(@RequestBody TicketBookingPayment payment){
        return service.preview(payment);
    }

    // STEP 2 - Pay
    @PostMapping("/pay/{id}")
    public TicketBookingPayment pay(@PathVariable String id){
        return service.makePayment(id);
    }

    // STEP 3 - Get Ticket
    @GetMapping("/ticket/{id}")
    public TicketBookingPayment ticket(@PathVariable String id){
        return service.getTicket(id);
    }

    // STEP 4 - Scan Ticket
    @GetMapping("/scan")
    public TicketBookingPayment scan(@RequestParam String qrCode){
        return service.scanTicket(qrCode);
    }
}