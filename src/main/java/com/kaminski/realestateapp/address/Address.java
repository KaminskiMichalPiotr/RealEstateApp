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
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String district;

    @OneToMany(mappedBy = "address")
    List<RealEstate> realEstates;

    public Address(AddressBuilder addressBuilder) {
        city = addressBuilder.city;
        country = addressBuilder.country;
        district = addressBuilder.district;
        id = addressBuilder.id;
    }

    public static AddressBuilder builder() {
        return new AddressBuilder();
    }


    public static final class AddressBuilder {
        private Long id;
        private String country;
        private String city;
        private String district;

        public AddressBuilder() {
        }

        public AddressBuilder baseOn(Address address) {
            city = address.city;
            country = address.country;
            district = address.district;
            id = address.id;
            return this;
        }

        public AddressBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public AddressBuilder withCountry(String country) {
            this.country = country;
            return this;
        }

        public AddressBuilder withCity(String city) {
            this.city = city;
            return this;
        }

        public AddressBuilder withDistrict(String district) {
            this.district = district;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
