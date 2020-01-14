package com.kaminski.realestateapp.realestate;

import com.kaminski.realestateapp.realestate.businessestablishment.BusinessEstablishment;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class RealEstateTest {

    @Test
    public void setPricePerSquareMeter() {
        long area = 50;
        long price = 50000;
        long pricePerM = price/area;
        RealEstate realEstate = new BusinessEstablishment(null,area,price,null,
                null,null,null,null);
        realEstate.setPricePerSquareMeter();
        assertEquals(pricePerM, realEstate.getPricePerSquareMeter());
    }
}