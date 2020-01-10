package com.kaminski.realestateapp.initializers;

import com.kaminski.realestateapp.address.Address;
import com.kaminski.realestateapp.address.AddressRepo;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddressInitializer {

    @Autowired
    private AddressRepo addressRepo;

    @PostConstruct
    public void loadAddresses() {
        JSONParser jsonParser = new JSONParser();
        try {
            InputStream is = getClass().getResourceAsStream("/pl.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            Object obj = jsonParser.parse(reader);

            JSONArray addresses = (JSONArray) obj;
            List<Address> addressList = new ArrayList<>();
            addresses.forEach(address -> addressList.add(parseAddressObject((JSONObject) address)));
            if (addressRepo.findAll().size() < addressList.size()) {
                addressRepo.deleteAll();
                addressRepo.saveAll(addressList);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private Address parseAddressObject(JSONObject address) {
        String country = (String) address.get("country");
        String city = (String) address.get("city");
        String district = (String) address.get("admin");
        return new Address(country, city, district);
    }


}
