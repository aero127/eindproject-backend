package nl.mtbrental.eindproject.service;


import nl.mtbrental.eindproject.exceptions.RecordNotFoundException;
import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BikeServiceImpl implements BikeService {

    @Autowired
    private BikeRepository bikeRepository;


    @Override
    public List<Bike> getBikes() { return bikeRepository.findAll(); }

    @Override
    public Optional<Bike> getBike(Long id) { return bikeRepository.findById(id); }

    @Override
    public Bike addBike(Bike bike) { return bikeRepository.save(bike); }

    @Override
    public void removeBike(Long id) { bikeRepository.deleteById(id); }

    @Override
    public void updateBike(Long id, Bike newBike) {
        if(!bikeRepository.existsById(id)) throw new RecordNotFoundException();
        Bike bike = bikeRepository.findById(id).get();
        bike.setQuantityTotal(newBike.getQuantityTotal());
        bikeRepository.save(bike);
    }


}
