package com.kaminski.realestateapp.realestate.house;

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

    @Column
    private int numberOfFloors;

    @Column
    private FinishingCondition finishingCondition;

    @Column
    private Long gardenArea;

    @Column
    private int numberOfRooms;

    @Column
    private int numberOfBathrooms;

    @Override
    public void setRealEstateType(RealEstateType realEstateType) {
        super.setRealEstateType(RealEstateType.HOUSE);
    }

}
