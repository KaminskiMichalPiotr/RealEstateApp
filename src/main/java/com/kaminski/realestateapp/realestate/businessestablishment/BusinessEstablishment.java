package com.kaminski.realestateapp.realestate.businessestablishment;

import com.kaminski.realestateapp.address.Address;
import com.kaminski.realestateapp.realestate.RealEstate;
import com.kaminski.realestateapp.realestate.RealEstateType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class BusinessEstablishment extends RealEstate {

    public BusinessEstablishment(Long id, Long area, Long price, Long pricePerSquareMeter, String thumbnailPath,
                                 Address address, RealEstateType realEstateType, BusinessEstablishmentType businessEstablishmentType) {
        super(id, area, price, pricePerSquareMeter, thumbnailPath, address, realEstateType);
        this.businessEstablishmentType = businessEstablishmentType;
    }

    @Column
    private BusinessEstablishmentType businessEstablishmentType;

    @Override
    public void setRealEstateType(RealEstateType realEstateType) {
        super.setRealEstateType(RealEstateType.BUSINESS_ESTABLISHMENT);
    }
}
