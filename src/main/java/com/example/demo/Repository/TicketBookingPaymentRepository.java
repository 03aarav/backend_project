package com.example.demo.Repository;

import com.example.demo.Model.TicketBookingPayment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TicketBookingPaymentRepository
        extends MongoRepository<TicketBookingPayment, String> {

    Optional<TicketBookingPayment> findByQrCode(String qrCode);
}