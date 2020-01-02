package com.kaminski.realestateapp.realestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class RealEstateService {

    @Autowired
    private RealEstateRepo realEstateRepo;

    public List<RealEstateDTO> getAllRealEstates() {
        return realEstateRepo.findAll().stream().map(RealEstateDTO::adaptFrom).collect(Collectors.toList());
    }


    public Optional<RealEstate> findById(Long id) {
        return realEstateRepo.findById(id);
    }

    public List<RealEstate> searchByCriteria(ParameterSearch parameterSearch) {
        RealEstateSpecification realEstateSpecification = new RealEstateSpecification(parameterSearch);
        return realEstateRepo.findAll(realEstateSpecification);
    }
}
