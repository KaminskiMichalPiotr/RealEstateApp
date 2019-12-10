package com.kaminski.realestateapp.realestate.house;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HouseService {

    @Autowired
    private HouseRepo houseRepo;

    public Optional<House> findHouseById(Long id){
        return houseRepo.findById(id);
    }

    public List<House> getHouses(){
        return houseRepo.findAll();
    }

    public House updateHouse(House house){
        return houseRepo.save(house);
    }

    public House saveHouse(House house) {
        return houseRepo.save(house);
    }

}
