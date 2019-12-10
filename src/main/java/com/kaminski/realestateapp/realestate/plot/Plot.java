package com.kaminski.realestateapp.realestate.plot;

import com.kaminski.realestateapp.address.Address;
import com.kaminski.realestateapp.announcement.Announcement;
import com.kaminski.realestateapp.realestate.RealEstate;
import com.kaminski.realestateapp.realestate.RealEstateType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Plot extends RealEstate {

    public Plot(Long id, Long area, Long price, Long pricePerSquareMeter, Address address, RealEstateType realEstateType,
                boolean isWaterAvailable, boolean isGasAvailable, boolean isEnergyAvailable, PlotType plotType) {
        super(id, area, price, pricePerSquareMeter, address, realEstateType);
        this.isWaterAvailable = isWaterAvailable;
        this.isGasAvailable = isGasAvailable;
        this.isEnergyAvailable = isEnergyAvailable;
        this.plotType = plotType;
    }

    @Column
    private boolean isWaterAvailable;

    @Column
    private boolean isGasAvailable;

    @Column
    private boolean isEnergyAvailable;

    @Column
    private PlotType plotType;

    @Override
    public void setRealEstateType(RealEstateType realEstateType) {
        super.setRealEstateType(RealEstateType.PLOT);
    }
}
