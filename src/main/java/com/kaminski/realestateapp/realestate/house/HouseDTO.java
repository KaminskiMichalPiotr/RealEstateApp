package com.kaminski.realestateapp.realestate.house;

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
public class HouseDTO extends RealEstateDTO {

    public HouseDTO(Long id, Long area, Long price, Long pricePerSquareMeter, String thumbnailPath, AddressDTO address,
                    String realEstateType, int numberOfFloors, Long gardenArea, int numberOfRooms) {
        super(id, area, price, pricePerSquareMeter, thumbnailPath, address, realEstateType);
        this.numberOfFloors = numberOfFloors;
        this.gardenArea = gardenArea;
        this.numberOfRooms = numberOfRooms;
    }

    private int numberOfFloors;

    private Long gardenArea;

    private int numberOfRooms;

    public static HouseDTO adaptFrom(House house) {
        return new HouseDTO(house.getId(), house.getArea(), house.getPrice(), house.getPricePerSquareMeter(),
                house.getThumbnailPath(), AddressDTO.adaptFrom(house.getAddress()), house.getRealEstateType().name(),
                house.getNumberOfFloors(), house.getGardenArea(), house.getNumberOfRooms());
    }

    public static House adaptTo(HouseDTO houseDTO){
        return new House(houseDTO.getId(), houseDTO.getArea(), houseDTO.getPrice(), houseDTO.getPricePerSquareMeter(),
                houseDTO.getThumbnailPath(), AddressDTO.adaptTo(houseDTO.getAddress()), RealEstateType.HOUSE,
                houseDTO.getNumberOfFloors(), houseDTO.getGardenArea(),  houseDTO.getNumberOfRooms()
        );
    }
}