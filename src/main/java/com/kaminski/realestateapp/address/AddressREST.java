package com.kaminski.realestateapp.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AddressREST {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAnnouncementById(@PathVariable Long id) {
        Optional<Address> address = addressService.findAddressById(id);
        if (address.isPresent()) {
            return ResponseEntity.ok().body(address.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Address>> getAnnouncements() {
        List<Address> addresses = addressService.getAddresses();
        return ResponseEntity.ok().body(addresses);
    }

}

