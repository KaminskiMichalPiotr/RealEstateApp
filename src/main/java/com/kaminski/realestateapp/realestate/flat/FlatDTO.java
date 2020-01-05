package com.kaminski.realestateapp.realestate.flat;

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
public class FlatDTO extends RealEstateDTO {

    public FlatDTO(Long id, Long area, Long price, Long pricePerSquareMeter, String thumbnailPath, AddressDTO address,
                   String realEstateType, Boolean pcvWindows, Boolean urbanHeating, Integer floorNumber,
                   Integer numberOfRooms) {
        super(id, area, price, pricePerSquareMeter, thumbnailPath, address, realEstateType);
        this.pcvWindows = pcvWindows;
        this.urbanHeating = urbanHeating;
        this.floorNumber = floorNumber;
        this.numberOfRooms = numberOfRooms;
    }

    private Boolean pcvWindows;

    private Boolean urbanHeating;

    private Integer floorNumber;

    private Integer numberOfRooms;

    public static FlatDTO adaptFrom(Flat flat) {
        return new FlatDTO(flat.getId(), flat.getArea(), flat.getPrice(), flat.getPricePerSquareMeter(),
                flat.getThumbnailPath(), AddressDTO.adaptFrom(flat.getAddress()), flat.getRealEstateType().name(),
                flat.getPcvWindows(), flat.getUrbanHeating(), flat.getFloorNumber(), flat.getNumberOfRooms());
    }

    public static Flat adaptTo(FlatDTO flatDTO){
        return new Flat(flatDTO.getId(), flatDTO.getArea(), flatDTO.getPrice(), flatDTO.getPricePerSquareMeter(),
                flatDTO.getThumbnailPath(), AddressDTO.adaptTo(flatDTO.getAddress()), RealEstateType.FLAT,
                flatDTO.getPcvWindows(), flatDTO.getUrbanHeating(),  flatDTO.getFloorNumber(), flatDTO.getNumberOfRooms()
        );
    }
}