package com.example.demo.Service;



import com.example.demo.Model.Payment;
import java.util.List;
import java.util.Optional;

public interface PaymentService {

    Payment createPayment(Payment payment);

    Optional<Payment> getPaymentById(String id);

    List<Payment> getAllPayments();

    List<Payment> getPaymentsByBookingId(String bookingId);

    void deletePayment(String id);


}