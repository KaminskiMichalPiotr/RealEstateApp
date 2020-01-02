package com.kaminski.realestateapp.realestate.house;

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
public class House extends RealEstate {

    public House(Long id, Long area, Long price, Long pricePerSquareMeter, String thumbnailPath, Address address,
                 RealEstateType realEstateType, int numberOfFloors, Long gardenArea, int numberOfRooms) {
        super(id, area, price, pricePerSquareMeter, thumbnailPath, address, realEstateType);
        this.numberOfFloors = numberOfFloors;
        this.gardenArea = gardenArea;
        this.numberOfRooms = numberOfRooms;
    }

    @Column
    private int numberOfFloors;

    @Column
    private Long gardenArea;

    @Column
    private int numberOfRooms;


    @Override
    public void setRealEstateType(RealEstateType realEstateType) {
        super.setRealEstateType(RealEstateType.HOUSE);
    }

}
