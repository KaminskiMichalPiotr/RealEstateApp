package com.kaminski.realestateapp.realestate.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/house", produces = MediaType.APPLICATION_JSON_VALUE)
public class HouseREST {

    @Autowired
    private HouseService houseService;

    @GetMapping("/{id}")
    public ResponseEntity<HouseDTO> getHouseById(@PathVariable Long id) {
        Optional<House> house = houseService.findHouseById(id);
        if (house.isPresent()) {
            return ResponseEntity.ok().body(HouseDTO.adaptFrom(house.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('WORKER')")
    public ResponseEntity<House> createFlat(@RequestBody House house) {
        if (houseService.findHouseById(house.getId()).isPresent()) {
            house = houseService.updateHouse(house);
        } else {
            house.setId(null);
            house = houseService.saveHouse(house);
        }
        return ResponseEntity.ok().body(house);
    }

    @GetMapping("")
    public ResponseEntity<List<House>> getFlats() {
        List<House> houses = houseService.getHouses();
        return ResponseEntity.ok().body(houses);
    }

}
