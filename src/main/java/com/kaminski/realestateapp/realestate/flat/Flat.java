package com.kaminski.realestateapp.realestate.flat;

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
public class Flat extends RealEstate {

    public Flat(Long id, Long area, Long price, Long pricePerSquareMeter, String thumbnailPath, Address address,
                RealEstateType realEstateType, Boolean pcvWindows, Boolean urbanHeating, Integer floorNumber,
                Integer numberOfRooms) {
        super(id, area, price, pricePerSquareMeter, thumbnailPath, address, realEstateType);
        this.pcvWindows = pcvWindows;
        this.urbanHeating = urbanHeating;
        this.floorNumber = floorNumber;
        this.numberOfRooms = numberOfRooms;
    }

    @Column
    private Boolean pcvWindows;

    @Column
    private Boolean urbanHeating;

    @Column
    private Integer floorNumber;

    @Column
    private Integer numberOfRooms;

    @Override
    public void setRealEstateType(RealEstateType realEstateType) {
        super.setRealEstateType(RealEstateType.FLAT);
    }

}
