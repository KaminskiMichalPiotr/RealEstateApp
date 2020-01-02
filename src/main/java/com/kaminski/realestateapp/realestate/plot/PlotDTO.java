package com.kaminski.realestateapp.realestate.plot;

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
public class PlotDTO extends RealEstateDTO {

    public PlotDTO(Long id, Long area, Long price, Long pricePerSquareMeter, String thumbnailPath, AddressDTO address,
                   RealEstateType realEstateType, boolean isWaterAvailable, boolean isGasAvailable,
                   boolean isEnergyAvailable, PlotType plotType) {
        super(id, area, price, pricePerSquareMeter, thumbnailPath, address, realEstateType);
        this.isWaterAvailable = isWaterAvailable;
        this.isGasAvailable = isGasAvailable;
        this.isEnergyAvailable = isEnergyAvailable;
        this.plotType = plotType;
    }

    private boolean isWaterAvailable;

    private boolean isGasAvailable;

    private boolean isEnergyAvailable;

    private PlotType plotType;

    public static PlotDTO adaptFrom(Plot plot){
        return new PlotDTO(plot.getId(),plot.getArea(),plot.getPrice(),plot.getPricePerSquareMeter(),
                plot.getThumbnailPath(),AddressDTO.adaptFrom(plot.getAddress()),plot.getRealEstateType(),
                plot.isWaterAvailable(), plot.isGasAvailable(), plot.isEnergyAvailable(),plot.getPlotType());
    }
}
