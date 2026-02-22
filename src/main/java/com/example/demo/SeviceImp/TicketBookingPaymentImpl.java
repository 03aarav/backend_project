package com.example.demo.SeviceImp;

import com.example.demo.Model.TicketBookingPayment;
import com.example.demo.Repository.TicketBookingPaymentRepository;
import com.example.demo.Service.TicketBookingPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TicketBookingPaymentImpl implements TicketBookingPaymentService {

    private final TicketBookingPaymentRepository repository;

    // STEP 1 - PREVIEW
    @Override
    public TicketBookingPayment preview(TicketBookingPayment request) {

        request.setStatus("PREVIEW");
        request.setCreatedAt(LocalDateTime.now());
        request.setUsed(false);   // default false

        return repository.save(request);
    }

    // STEP 2 - PAYMENT
    @Override
    public TicketBookingPayment makePayment(String id) {

        TicketBookingPayment booking = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus("PAID");
        booking.setQrCode("QR_" + booking.getId());
        booking.setUsed(false);

        return repository.save(booking);
    }

    // STEP 3 - GET TICKET
    @Override
    public TicketBookingPayment getTicket(String id) {

        TicketBookingPayment booking = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        if (!"PAID".equals(booking.getStatus())) {
            throw new RuntimeException("Payment not completed");
        }

        return booking;
    }

    // STEP 4 - SCAN TICKET
    @Override
    public TicketBookingPayment scanTicket(String qrCode) {

        TicketBookingPayment booking = repository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Invalid QR Code"));

        if (!"PAID".equals(booking.getStatus())) {
            throw new RuntimeException("Ticket not paid");
        }

        if (Boolean.TRUE.equals(booking.getUsed())) {
            throw new RuntimeException("Ticket already used");
        }

        booking.setUsed(true);

        return repository.save(booking);
    }
}