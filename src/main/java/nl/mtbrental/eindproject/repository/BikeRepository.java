package nl.mtbrental.eindproject.repository;

import nl.mtbrental.eindproject.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bike, Long> {
    Bike getById(long id);
}
