package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.model.Booking;
import nl.mtbrental.eindproject.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired
    BookingRepository bookingRepository;


    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookings(long id) {
        return bookingRepository.getById(id);
    }

    public Booking addBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void removeBooking(long id) {
    bookingRepository.deleteById(id);
    }

    public void updateBooking(long id) {

    }

    @Override
    public List<Booking> getBookingsForBike(Long bikeId) {
        return null;
    }

    @Override
    public List<Booking> getBookingsOnDate(LocalDateTime date) {
        return null;
    }

    @Override
    public List<Booking> getBookingsByUsername(String username) {
        return null;
    }

    @Override
    public Booking saveBooking(Booking booking, Long bikeId, String username, String startTime, LocalDateTime date) {
        return null;
    }

}
