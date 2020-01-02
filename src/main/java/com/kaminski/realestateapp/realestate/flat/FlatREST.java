package com.kaminski.realestateapp.realestate.flat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/flat", produces = MediaType.APPLICATION_JSON_VALUE)
public class FlatREST {

    @Autowired
    private FlatService flatService;

    @GetMapping("/{id}")
    public ResponseEntity<FlatDTO> getFlatById(@PathVariable Long id) {
        Optional<Flat> flat = flatService.findFlatById(id);
        if (flat.isPresent()) {
            return ResponseEntity.ok().body(FlatDTO.adaptFrom(flat.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('WORKER')")
    public ResponseEntity<Flat> createFlat(@RequestBody Flat flat) {
        if (flatService.findFlatById(flat.getId()).isPresent()) {
            flat = flatService.updateFlat(flat);
        } else {
            flat.setId(null);
            flat = flatService.saveFlat(flat);
        }
        return ResponseEntity.ok().body(flat);
    }

    @GetMapping("")
    public ResponseEntity<List<Flat>> getFlats() {
        List<Flat> flats = flatService.getFlats();
        return ResponseEntity.ok().body(flats);
    }


}
