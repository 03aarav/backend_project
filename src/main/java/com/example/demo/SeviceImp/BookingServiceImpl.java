package com.example.demo.SeviceImp; // Corrected package name

import com.example.demo.Model.Booking;
import com.example.demo.Repository.BookingRepository;
import com.example.demo.Service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;

    @Override
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> getBookingById(String id) { // Return Optional<Booking> as per the Service interface
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    @Override
    public Booking updateBooking(String id, Booking updatedBooking) {
        return bookingRepository.findById(id)
                .map(existing -> {
                    existing.setUserId(updatedBooking.getUserId());
                    existing.setTravelerId(updatedBooking.getTravelerId());
                    existing.setMuseumId(updatedBooking.getMuseumId());
                    existing.setPaymentStatus(updatedBooking.getPaymentStatus());
                    existing.setNoOfTravelers(updatedBooking.getNoOfTravelers());
                    existing.setTotalCost(updatedBooking.getTotalCost());
                    existing.setDate(updatedBooking.getDate());
                    existing.setTime(updatedBooking.getTime());
                    existing.setModeOfBooking(updatedBooking.getModeOfBooking());
                    existing.setCheckedIn(updatedBooking.isCheckedIn());
                    return bookingRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
    }

    @Override
    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}