package com.kaminski.realestateapp.realestate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kaminski.realestateapp.address.AddressDTO;
import com.kaminski.realestateapp.realestate.businessestablishment.BusinessEstablishmentDTO;
import com.kaminski.realestateapp.realestate.flat.FlatDTO;
import com.kaminski.realestateapp.realestate.house.HouseDTO;
import com.kaminski.realestateapp.realestate.plot.PlotDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "realEstateType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlotDTO.class, name = "PLOT"),
        @JsonSubTypes.Type(value = HouseDTO.class, name = "HOUSE"),
        @JsonSubTypes.Type(value = FlatDTO.class, name = "FLAT"),
        @JsonSubTypes.Type(value = BusinessEstablishmentDTO.class, name = "BUSINESS_ESTABLISHMENT")
})
public class RealEstateDTO {

    private Long id;
    private Long area;
    private Long price;
    private Long pricePerSquareMeter;
    private String thumbnailPath;
    private AddressDTO address;
    private String realEstateType;

    public static RealEstateDTO adaptFrom(RealEstate realEstate) {
        return new RealEstateDTO(realEstate.getId(), realEstate.getArea(), realEstate.getPrice()
                , realEstate.getPricePerSquareMeter(), realEstate.getThumbnailPath(), AddressDTO.adaptFrom(realEstate.getAddress()),
                realEstate.getRealEstateType().name());
    }

    public void setPricePerSquareMeter() {
        if (area != null && price != null) {
            this.pricePerSquareMeter = price / area;
        }
    }

}
