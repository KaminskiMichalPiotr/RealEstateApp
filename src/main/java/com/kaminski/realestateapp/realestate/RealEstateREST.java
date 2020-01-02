package com.kaminski.realestateapp.realestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/real-estate", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class RealEstateREST {

    @Autowired
    RealEstateRepo realEstateRepo;

    @Autowired
    RealEstateService realEstateService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('WORKER')")
    public ResponseEntity<RealEstate> createFlat(@RequestBody RealEstate realEstate) {
        realEstate = realEstateRepo.save(realEstate);
        return ResponseEntity.ok().body(realEstate);
    }

    @GetMapping("")
    @Transactional
    public ResponseEntity<List<RealEstateDTO>> getRealEstates() {
        List<RealEstateDTO> realEstates = realEstateService.getAllRealEstates();
        return ResponseEntity.ok().body(realEstates);
    }

    @PutMapping("/search")
    @Transactional
    public ResponseEntity<List<RealEstateDTO>> getRealEstatesByCriteria(@RequestBody ParameterSearch parameterSearch) {
        List<RealEstate> realEstates = realEstateService.searchByCriteria(parameterSearch);
        List<RealEstateDTO> realEstateDTOS = realEstates.stream().map(RealEstateDTO::adaptFrom).collect(Collectors.toList());
        return ResponseEntity.ok().body(realEstateDTOS);
    }

    @GetMapping("/type")
    public ResponseEntity<List<RealEstateType>> getRealEstateType() {
        return ResponseEntity.ok().body(Arrays.asList(RealEstateType.values()));
    }

}
