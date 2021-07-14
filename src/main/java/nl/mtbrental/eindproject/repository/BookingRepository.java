package nl.mtbrental.eindproject.repository;

import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.model.Booking;
import nl.mtbrental.eindproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
        Booking getById(Long id);

        List<Booking> findBookingByDate(LocalDateTime date);

        List<Booking> findBookingByUser(User user);

        List<Booking> findByBike(Bike bike);

        List<Booking> findByUser(User user);
        }
