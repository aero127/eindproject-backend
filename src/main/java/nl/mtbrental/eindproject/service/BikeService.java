package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.model.Bike;

import java.util.List;

public interface BikeService {

    List<Bike> getBike();
    Bike getBikes(Long id);
    Bike addBike(Bike bike);
    void removeBike(Long id);
    void updateBike(Long id, Bike bike);


}
