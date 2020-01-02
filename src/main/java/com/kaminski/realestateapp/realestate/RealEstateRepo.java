package com.kaminski.realestateapp.realestate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RealEstateRepo extends JpaRepository<RealEstate, Long>, JpaSpecificationExecutor<RealEstate> {

    List<RealEstate> findByRealEstateType(RealEstateType realEstateType);

}
