package com.example.demo.SeviceImp;

import com.example.demo.Model.Payment;
import com.example.demo.Repository.PaymentRepository;
import com.example.demo.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List; import java.util.Optional;

@Service @RequiredArgsConstructor public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> getPaymentById(String id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public List<Payment> getPaymentsByBookingId(String bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    @Override
    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }

}