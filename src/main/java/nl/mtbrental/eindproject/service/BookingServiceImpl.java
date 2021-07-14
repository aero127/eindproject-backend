package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.exceptions.BadRequestException;
import nl.mtbrental.eindproject.exceptions.NotFoundException;
import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.model.Booking;
import nl.mtbrental.eindproject.repository.BikeRepository;
import nl.mtbrental.eindproject.repository.BookingRepository;
import nl.mtbrental.eindproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private BookingRepository bookingRepository;
    private BikeRepository bikeRepository;
    private UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BikeRepository bikeRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.bikeRepository = bikeRepository;
    }



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



    public List<Booking> getBookingsOnDate(LocalDateTime date) {
        return bookingRepository.findBookingByDate(date);
    }




    @Override
    public List<Booking> getBookingsByUsername(String username) {
        return bookingRepository.findBookingByUser(username);
    }

//    @Override
//    public Booking saveBooking(Booking booking, Long bikeId, String username) {
//        return bookingRepository.save(booking);
//    }

    @Override
    public Booking saveBooking(Booking booking, Long bikeId, String username) {
        var optionalUser = userRepository.findById(username);
        var optionalBike = bikeRepository.findById(bikeId);

        if (optionalUser.isPresent() && optionalBike.isPresent()) {
            var user = optionalUser.get();
            var bike = optionalBike.get();

            var overlappingStartBookings = bookingRepository.findBookingByDate(booking.getDate());
            if (overlappingStartBookings.size() > 250 ) {
                throw new BadRequestException();
            }

            booking.setUser(user);
            booking.setBike(bike);
            return bookingRepository.save(booking);
        } else {
            throw new NotFoundException();
        }
    }


    @Override
    public List<Booking> getBookingsForBike(Long bikeId) {
        return bookingRepository.findByBike(bikeId);
    }



    public List<Booking> getBookingsForUser(String username) {
        var optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            return bookingRepository.findByUser(user);
        } else {
            throw new NotFoundException();
        }
    }
}