package com.kaminski.realestateapp.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressREST {

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.findAddressById(id);
        if (address.isPresent()) {
            return ResponseEntity.ok().body(AddressDTO.adaptFrom(address.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<AddressDTO>> getAddresses() {
        List<Address> addresses = addressService.getAddresses();
        List<AddressDTO> addressDTOS = addresses.stream().map(AddressDTO::adaptFrom).collect(Collectors.toList());
        return ResponseEntity.ok().body(addressDTOS);
    }

}

