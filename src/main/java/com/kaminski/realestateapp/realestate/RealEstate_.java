package com.kaminski.realestateapp.realestate;

import com.kaminski.realestateapp.address.Address;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(RealEstate.class)
public class RealEstate_ {

    public static volatile SingularAttribute<RealEstate, Long> id;
    public static volatile SingularAttribute<RealEstate, Long> area;
    public static volatile SingularAttribute<RealEstate, Long> price;
    public static volatile SingularAttribute<RealEstate, Long> pricePerSquareMeter;
    public static volatile SingularAttribute<RealEstate, RealEstateType> realEstateType;
    public static volatile SingularAttribute<RealEstate, Address> address;

}
