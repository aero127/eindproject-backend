package com.mtbrental.eindproject.service;

import com.mtbrental.eindproject.model.Bike;

import java.util.List;

public interface BikeService {

    List<Bike> getBikes();
    Bike getBikes(long id);
    Bike addBike(Bike bike);
    void removeBike(long id);
    void updateBike(long id, Bike bike);
}
