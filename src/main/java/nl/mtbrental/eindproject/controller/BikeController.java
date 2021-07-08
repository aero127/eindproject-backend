package nl.mtbrental.eindproject.controller;


import nl.mtbrental.eindproject.dto.BikeDto;
import nl.mtbrental.eindproject.dto.BikeInputDto;
import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.service.BikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/bikes")
public class BikeController {

    private final BikeService bikeService;

    public BikeController(BikeService bikeService) {
        this.bikeService = bikeService;
    }


    @GetMapping("")
    public ResponseEntity<Object> getBikes() {return ResponseEntity.ok(bikeService.getBikes());}

//    @PostMapping("")
//    public ResponseEntity<Object> addBike(@RequestBody Bike bike) {
//        bikeService.addBike(bike);
//        return ResponseEntity.ok("added");
//    }

    @PostMapping
    public BikeDto saveBoat(@RequestBody BikeInputDto dto) {
        var bike = bikeService.addBike(dto.toBike());
        return BikeDto.fromBike(bike);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getBikes(@PathVariable("id") long id) {
        Bike bike = (Bike) bikeService.getBikes(id);
        return ResponseEntity.ok(bike);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> removeBike(@PathVariable("id") long id) {
        bikeService.removeBike(id);
        return ResponseEntity.noContent().build();
    }

}
