package nl.mtbrental.eindproject.repository;

import nl.mtbrental.eindproject.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
        Booking getById(long id);
        }
