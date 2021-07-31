package nl.mtbrental.eindproject.service;


import nl.mtbrental.eindproject.model.Booking;


import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    List<Booking> getBookings();
    Booking getBooking(Long id);

    void removeBooking(Long id);

    List<Booking> getBookingsOnDate(LocalDateTime date);

    List<Booking> getBookingsByUsername(String username);

    Booking saveBooking(Booking booking, Long bikeId, String username);

    List<Booking> getBookingsForBike(Long bikeId);

}
