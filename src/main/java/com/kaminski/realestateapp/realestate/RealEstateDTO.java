package com.kaminski.realestateapp.realestate;

import com.kaminski.realestateapp.address.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RealEstateDTO {

    private Long id;
    private Long area;
    private Long price;
    private Long pricePerSquareMeter;
    private String thumbnailPath;
    private AddressDTO address;
    private RealEstateType realEstateType;

    public static RealEstateDTO adaptFrom(RealEstate realEstate){
        return new RealEstateDTO(realEstate.getId(),realEstate.getArea(),realEstate.getPrice()
                ,realEstate.getPricePerSquareMeter(),realEstate.getThumbnailPath(), AddressDTO.adaptFrom(realEstate.getAddress()),
                realEstate.getRealEstateType());
    }

}
