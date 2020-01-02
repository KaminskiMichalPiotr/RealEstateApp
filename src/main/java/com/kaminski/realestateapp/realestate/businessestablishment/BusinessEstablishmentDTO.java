package com.kaminski.realestateapp.realestate.businessestablishment;


import com.kaminski.realestateapp.address.AddressDTO;
import com.kaminski.realestateapp.realestate.RealEstateDTO;
import com.kaminski.realestateapp.realestate.RealEstateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BusinessEstablishmentDTO extends RealEstateDTO {

    public BusinessEstablishmentDTO(Long id, Long area, Long price, Long pricePerSquareMeter, String thumbnailPath,
                                    AddressDTO address, RealEstateType realEstateType, BusinessEstablishmentType businessEstablishmentType) {
        super(id, area, price, pricePerSquareMeter, thumbnailPath, address, realEstateType);
        this.businessEstablishmentType = businessEstablishmentType;
    }

    private BusinessEstablishmentType businessEstablishmentType;

    public static BusinessEstablishmentDTO adaptFrom(BusinessEstablishment businessEstablishment){
        return new BusinessEstablishmentDTO(businessEstablishment.getId(),businessEstablishment.getArea(),
                businessEstablishment.getPrice(),businessEstablishment.getPricePerSquareMeter(),
                businessEstablishment.getThumbnailPath(), AddressDTO.adaptFrom(businessEstablishment.getAddress()),
                businessEstablishment.getRealEstateType(), businessEstablishment.getBusinessEstablishmentType());
    }
}