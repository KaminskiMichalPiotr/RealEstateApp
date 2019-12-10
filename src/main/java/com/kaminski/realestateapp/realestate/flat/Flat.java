package com.kaminski.realestateapp.realestate.flat;

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

    @Column
    Boolean pcvWindows;

    @Column
    Boolean urbanHeating;

    @Column
    Integer floor;

    @Column
    Integer numberOfRooms;

    @Override
    public void setRealEstateType(RealEstateType realEstateType) {
        super.setRealEstateType(RealEstateType.FLAT);
    }

}
