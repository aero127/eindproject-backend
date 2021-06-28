package com.mtbrental.eindproject.controller;


import com.mtbrental.eindproject.model.Bike;
import com.mtbrental.eindproject.service.BikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;

@RestController
@RequestMapping("/bikes")
public class BikeController {

    @Autowired
    private BikeService bikeService;

    @GetMapping("")
    public ResponseEntity<Object> getBikes() {return ResponseEntity.ok(bikeService.getBikes());}

    @PostMapping("")
    public ResponseEntity<Object> addBike(@RequestBody Bike bike) {
        bikeService.addBike(bike);
        return ResponseEntity.ok("added");
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
