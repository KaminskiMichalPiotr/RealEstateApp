package com.kaminski.realestateapp.address;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddressDTO {

    private Long id;
    private String country;
    private String city;
    private String district;

    public static AddressDTO adaptFrom(Address address) {
        return new AddressDTO(address.getId(), address.getCountry(), address.getCity(), address.getDistrict());
    }

    public static Address adaptTo(AddressDTO addressDTO) {
        return new Address(addressDTO.getCountry(), addressDTO.getCity(), addressDTO.getDistrict());
    }

}
