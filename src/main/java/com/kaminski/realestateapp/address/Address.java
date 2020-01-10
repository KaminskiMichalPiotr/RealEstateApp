package com.kaminski.realestateapp.address;

import com.kaminski.realestateapp.realestate.RealEstate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @Column()
    private String country;

    @Column
    private String city;

    @Column
    private String district;

    @OneToMany(mappedBy = "address", fetch = FetchType.LAZY)
    List<RealEstate> realEstates;

    public Address(String country, String city, String district) {
        this.country = country;
        this.city = city;
        this.district = district;
    }
}
