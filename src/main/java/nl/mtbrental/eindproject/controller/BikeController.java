package nl.mtbrental.eindproject.controller;


import nl.mtbrental.eindproject.dto.BikeDto;
import nl.mtbrental.eindproject.dto.BikeInputDto;
import nl.mtbrental.eindproject.model.Bike;
import nl.mtbrental.eindproject.model.User;
import nl.mtbrental.eindproject.service.BikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public BikeDto saveBike(@RequestBody BikeInputDto dto) {
        var bike = bikeService.addBike(dto.toBike());
        return BikeDto.fromBike(bike);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getBike(@PathVariable("id") Long id) {
        return ResponseEntity.ok(bikeService.getBike(id));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> removeBike(@PathVariable("id") long id) {
        bikeService.removeBike(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public BikeDto updateBike(@PathVariable("id") @RequestBody BikeInputDto dto) {
        var bike = bikeService.addBike(dto.toBike());
        return BikeDto.fromBike(bike);
    }
}



