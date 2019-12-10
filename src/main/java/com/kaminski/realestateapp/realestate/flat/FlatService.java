package com.kaminski.realestateapp.realestate.flat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FlatService {

    @Autowired
    private FlatRepo flatRepo;

    public Optional<Flat> findFlatById(Long id){
        return flatRepo.findById(id);
    }

    public List<Flat> getFlats(){
        return flatRepo.findAll();
    }

    public Flat updateFlat(Flat flat){
        return flatRepo.save(flat);
    }

    public Flat saveFlat(Flat flat) {
        return flatRepo.save(flat);
    }

}
