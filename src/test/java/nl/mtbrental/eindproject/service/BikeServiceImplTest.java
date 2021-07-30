package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.model.User;
import nl.mtbrental.eindproject.repository.BikeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BikeServiceImplTest {

    @Mock
    BikeRepository bikeRepository;

    @InjectMocks
    private BikeServiceImpl bikeServiceImpl;

    @Captor
    ArgumentCaptor<Bike> bikeCaptor;


    @Test
    public void getAllBikesTest() {
        when(bikeRepository.findAll()).thenReturn(List.of(new Bike(), new Bike(), new Bike()));
        List<Bike> bikeList = bikeServiceImpl.getBikes();
        assertEquals(3, bikeList.size());
    }

    @Test
    public void getBikeTest() {
        Bike bike = new Bike();
        bike.setId(1L);
        Long id = bike.getId();
        when(bikeRepository.findById(id)).thenReturn(Optional.of(bike));
        Optional<Bike> bikeOptional = bikeServiceImpl.getBike(id);
        assertTrue(bikeOptional.isPresent());
        assertEquals(id, bikeOptional.get().getId());
    }

    @Test
    void addBikeTest() {
        Bike initialBike = new Bike();
        initialBike.setId(1L);
        initialBike.setBikeName("testbike");
        initialBike.setQuantityTotal(250);
        initialBike.setPricePerDay(55L);
        when(bikeRepository.save(initialBike)).thenReturn(initialBike);
        bikeServiceImpl.addBike(initialBike);
        verify(bikeRepository).save(bikeCaptor.capture());
        Bike newBike = bikeCaptor.getValue();
        assertThat(initialBike.getId().equals(newBike.getId()));

    }

    @Test
    public void removeBikeTest() {
        Bike bike = new Bike();
        bike.setId(1L);
        bikeServiceImpl.removeBike(bike.getId());
        verify(bikeRepository).deleteById(bike.getId());
    }

    @Test
    public void updateBikeTest() {
        Bike initialBike = new Bike();
        initialBike.setBikeName("testbike2");
        initialBike.setQuantityTotal(240);
        initialBike.setPricePerDay(25L);
        initialBike.setId(2L);
        Bike update = new Bike();
        update.setQuantityTotal(99);
        when(bikeRepository.existsById(update.getId())).thenReturn(true);
        when(bikeRepository.findById(update.getId())).thenReturn(Optional.of(initialBike));
        bikeServiceImpl.updateBike(update.getId(), update);
        verify(bikeRepository).save(bikeCaptor.capture());
        Bike savedBike = bikeCaptor.getValue();
        assertEquals(savedBike.getQuantityTotal(), update.getQuantityTotal());
    }

}