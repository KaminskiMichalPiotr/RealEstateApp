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
                                    AddressDTO address, String realEstateType, BusinessEstablishmentType businessEstablishmentType) {
        super(id, area, price, pricePerSquareMeter, thumbnailPath, address, realEstateType);
        this.businessEstablishmentType = businessEstablishmentType;
    }

    private BusinessEstablishmentType businessEstablishmentType;

    public static BusinessEstablishmentDTO adaptFrom(BusinessEstablishment establishment) {
        return new BusinessEstablishmentDTO(establishment.getId(), establishment.getArea(),
                establishment.getPrice(), establishment.getPricePerSquareMeter(),
                establishment.getThumbnailPath(), AddressDTO.adaptFrom(establishment.getAddress()),
                establishment.getRealEstateType().name(), establishment.getBusinessEstablishmentType());
    }

    public static BusinessEstablishment adaptTo(BusinessEstablishmentDTO dto) {
        return new BusinessEstablishment(dto.getId(), dto.getArea(),
                dto.getPrice(), dto.getPricePerSquareMeter(),
                dto.getThumbnailPath(), AddressDTO.adaptTo(dto.getAddress()),
                RealEstateType.BUSINESS_ESTABLISHMENT,
                dto.getBusinessEstablishmentType()
        );
    }
}