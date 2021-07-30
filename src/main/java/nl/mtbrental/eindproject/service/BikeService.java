package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.model.Bike;

import java.util.List;
import java.util.Optional;

public interface BikeService {

    List<Bike> getBikes();
    Optional<Bike> getBike(Long id);
    Bike addBike(Bike bike);
    void removeBike(Long id);
    void updateBike(Long id, Bike bike);


}
