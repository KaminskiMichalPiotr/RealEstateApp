package com.kaminski.realestateapp.realestate;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.kaminski.realestateapp.address.Address;
import com.kaminski.realestateapp.realestate.businessestablishment.BusinessEstablishment;
import com.kaminski.realestateapp.realestate.businessestablishment.BusinessEstablishmentService;
import com.kaminski.realestateapp.realestate.flat.Flat;
import com.kaminski.realestateapp.realestate.house.House;
import com.kaminski.realestateapp.realestate.plot.Plot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "realEstateType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Plot.class, name = "PLOT"),
        @JsonSubTypes.Type(value = House.class, name = "HOUSE"),
        @JsonSubTypes.Type(value = Flat.class, name = "FLAT"),
        @JsonSubTypes.Type(value = BusinessEstablishment.class, name = "BUSINESS_ESTABLISHMENT")
})
public abstract class RealEstate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private Long area;

    @Column
    private Long price;

    @Column
    private Long pricePerSquareMeter;

    @Column
    private String thumbnailPath;

    @ManyToOne
    @JoinColumn
    private Address address;

    @Column
    RealEstateType realEstateType;

    public void setPricePerSquareMeter() {
        if(area != null && price != null){
            this.pricePerSquareMeter = price/area;
            return;
        }
    }
}
