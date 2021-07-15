package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.model.Booking;
import nl.mtbrental.eindproject.model.User;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingService {

    List<Booking> getBookings();
    Booking getBookings(long id);
//    Booking addBooking(Booking booking);
//    void removeBooking(long id);
//    void updateBooking(long id);

//    List<Booking> getBookingsForBike(Long bikeId);

    List<Booking> getBookingsOnDate(LocalDateTime date, String username, Long bikeId);

    List<Booking> getBookingsByUsername(String username);

    Booking saveBooking(Booking booking, Long bikeId, String username);

    List<Booking> getBookingsForBike(Long bikeId);

//    List<Booking> getBookingsForUser(String username);
}
