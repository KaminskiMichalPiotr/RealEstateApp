package com.kaminski.realestateapp.realestate;

import com.kaminski.realestateapp.address.Address;
import com.kaminski.realestateapp.announcement.Announcement;
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

    @ManyToOne
    private Address address;

    @Column
    RealEstateType realEstateType;

//    @OneToOne(mappedBy = "realEstate")
//    Announcement announcement;

}
