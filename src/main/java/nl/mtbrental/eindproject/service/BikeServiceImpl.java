package nl.mtbrental.eindproject.service;


import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {
    @Autowired
    BikeRepository bikeRepository;

    public List<Bike> getBikes() { return bikeRepository.findAll(); }

    public Bike getBikes(long id) { return bikeRepository.getById(id); }

    public Bike addBike(Bike bike) { return bikeRepository.save(bike); }

    public void removeBike(long id) { bikeRepository.deleteById(id); }

    public void updateBike(long id, Bike bike) {}
}
