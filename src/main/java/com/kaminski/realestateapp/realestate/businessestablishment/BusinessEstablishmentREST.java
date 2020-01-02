package com.kaminski.realestateapp.realestate.businessestablishment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/business-establishment", produces = MediaType.APPLICATION_JSON_VALUE)
public class BusinessEstablishmentREST {

    @Autowired
    private BusinessEstablishmentService businessEstablishmentService;

    @GetMapping("/{id}")
    public ResponseEntity<BusinessEstablishmentDTO> getFlatById(@PathVariable Long id) {
        Optional<BusinessEstablishment> businessEstablishment = businessEstablishmentService.findFlatById(id);
        if (businessEstablishment.isPresent()) {
            return ResponseEntity.ok().body(BusinessEstablishmentDTO.adaptFrom(businessEstablishment.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('WORKER')")
    public ResponseEntity<BusinessEstablishment> createFlat(@RequestBody BusinessEstablishment businessEstablishment) {
        if (businessEstablishmentService.findFlatById(businessEstablishment.getId()).isPresent()) {
            businessEstablishment = businessEstablishmentService.updateFlat(businessEstablishment);
        } else {
            businessEstablishment.setId(null);
            businessEstablishment = businessEstablishmentService.saveFlat(businessEstablishment);
        }
        return ResponseEntity.ok().body(businessEstablishment);
    }

    @GetMapping("")
    public ResponseEntity<List<BusinessEstablishment>> getFlats() {
        List<BusinessEstablishment> businessEstablishments = businessEstablishmentService.getFlats();
        return ResponseEntity.ok().body(businessEstablishments);
    }

    @GetMapping("/type")
    public ResponseEntity<List<BusinessEstablishmentType>> getBusinessEstablishmentType() {
        return ResponseEntity.ok().body(Arrays.asList(BusinessEstablishmentType.values()));
    }

}
