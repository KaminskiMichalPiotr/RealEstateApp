package com.kaminski.realestateapp.realestate;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParameterSearch {

    private String realEstateType;
    private String city;
    private Long minArea;
    private Long maxArea;
    private Long minPrice;
    private Long maxPrice;
    private Long minPricePerM;
    private Long maxPricePerM;
    private String district;

}
