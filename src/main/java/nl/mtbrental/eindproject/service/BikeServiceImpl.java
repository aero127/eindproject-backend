package nl.mtbrental.eindproject.service;


import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.repository.BikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BikeServiceImpl implements BikeService {

    private BikeRepository repository;

    @Autowired
    public BikeServiceImpl(BikeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Bike> getBike() { return repository.findAll(); }

    @Override
    public Bike getBikes(long id) { return repository.getById(id); }

    @Override
    public Bike addBike(Bike bike) { return repository.save(bike); }

    @Override
    public void removeBike(long id) { repository.deleteById(id); }

    @Override
    public void updateBike(long id, Bike bike) {}


    
}
