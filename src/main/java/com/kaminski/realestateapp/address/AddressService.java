package com.kaminski.realestateapp.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    public Optional<Address> findAddressById(Long id) {
        return addressRepo.findById(id);
    }

    public List<Address> getAddresses() {
        return addressRepo.findAll();
    }

    public Address findAddressByCity(String city) {
        return addressRepo.findByCity(city);
    }
}
