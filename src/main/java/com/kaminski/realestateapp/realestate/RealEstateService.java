package com.kaminski.realestateapp.realestate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RealEstateService {

    @Autowired
    private RealEstateRepo realEstateRepo;

    public List<RealEstate> getAllRealEstates(){
        return realEstateRepo.findAll();
    }

    public List<RealEstate> getRealEstateByType(RealEstateType realEstateType){
        return realEstateRepo.findByRealEstateType(realEstateType);
    }

    public boolean removeRealEstate(Long id){
        if(realEstateRepo.findById(id).isPresent()) {
            realEstateRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public int getRealEstateCount(){
        return realEstateRepo.findAll().size();
    }

    public List<RealEstate> getRealEstateByPagination(int startIndex, int endIndex){
        List<RealEstate> realEstates = realEstateRepo.findAll();
        return realEstates.subList(startIndex, endIndex);
    }

    public int getRealEstateCountByType(RealEstateType realEstateType){
        return realEstateRepo.findByRealEstateType(realEstateType).size();
    }

    public List<RealEstate> getRealEstateByTypeAndPagination(RealEstateType realEstateType, int startIndex, int endIndex){
        List<RealEstate> byRealEstateType = realEstateRepo.findByRealEstateType(realEstateType);
        return byRealEstateType.subList(startIndex, endIndex);
    }


}
