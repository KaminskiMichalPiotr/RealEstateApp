package com.kaminski.realestateapp.realestate.businessestablishment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusinessEstablishmentService {

    @Autowired
    private BusinessEstablishmentRepo businessEstablishmentRepo;

    public Optional<BusinessEstablishment> findFlatById(Long id){
        return businessEstablishmentRepo.findById(id);
    }

    public List<BusinessEstablishment> getFlats(){
        return businessEstablishmentRepo.findAll();
    }

    public BusinessEstablishment updateFlat(BusinessEstablishment businessEstablishment){
        return businessEstablishmentRepo.save(businessEstablishment);
    }

    public BusinessEstablishment saveFlat(BusinessEstablishment businessEstablishment) {
        return businessEstablishmentRepo.save(businessEstablishment);
    }

}
