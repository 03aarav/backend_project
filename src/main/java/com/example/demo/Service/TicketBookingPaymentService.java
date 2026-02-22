package com.example.demo.Service;

import com.example.demo.Model.TicketBookingPayment;

public interface TicketBookingPaymentService {

    TicketBookingPayment preview(TicketBookingPayment request);

    TicketBookingPayment makePayment(String id);

    TicketBookingPayment getTicket(String id);

    TicketBookingPayment scanTicket(String qrCode); // NEW METHOD
}