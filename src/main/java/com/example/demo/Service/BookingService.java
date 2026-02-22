package com.example.demo.Service;



import com.example.demo.Model.Booking;
import java.util.List;
import java.util.Optional;

public interface BookingService {
    Booking createBooking(Booking booking);
    Optional<Booking> getBookingById(String id);
    List<Booking> getAllBookings();
    Booking updateBooking(String id, Booking booking);
    void deleteBooking(String id);
}
