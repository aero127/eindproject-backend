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

    private final BookingRepository bookingRepository;
    private final BikeRepository bikeRepository;
    private final UserRepository userRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, BikeRepository bikeRepository, UserRepository userRepository) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.bikeRepository = bikeRepository;
    }



    public List<Booking> getBookings() {
        return bookingRepository.findAll();
    }

    public Booking getBookings(Long id) {
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
        var optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            return bookingRepository.findByUser(user);
        } else {
            throw new NotFoundException();
        }
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
            bike.setQuantityTotal((long) (bike.getQuantityTotal() - booking.getAmount()));
            System.out.println("voorraad fietsen over van bikeId " + bike.getId() + ": " + bike.getQuantityTotal());
                        if (bike.getQuantityTotal() < 1 ) {
                throw new BadRequestException();
            }
            if (booking.isHelmet()) {
                     System.out.println("if");
                     booking.setPrice((Long) (booking.getAmount()*bike.getPricePerDay() + (booking.getAmount()*4)));
            } else {
                     System.out.println("else");
                     booking.setPrice((Long) (booking.getAmount() * bike.getPricePerDay()));
                 }
            System.out.println("de prijs van deze booking is: €" + booking.getPrice());
//            System.out.println(booking.isHelmet());
            booking.setUser(user);
            booking.setBike(bike);
            return bookingRepository.save(booking);
        } else {
            throw new NotFoundException();
        }
    }


    @Override
    public List<Booking> getBookingsForBike(Long bikeId) {
        var optionalBike = bikeRepository.findById(bikeId);

        if (optionalBike.isPresent()) {
            var bike = optionalBike.get();
            return bookingRepository.findByBike(bike);
        } else {
            throw new NotFoundException();
        }
    }


    public List<Booking> getBookingsForUser(String username) {
        var optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            return bookingRepository.findBookingByUser(user);
        } else {
            throw new NotFoundException();
        }
    }
}
