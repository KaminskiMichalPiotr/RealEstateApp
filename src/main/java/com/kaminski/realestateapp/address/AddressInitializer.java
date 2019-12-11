package com.kaminski.realestateapp.address;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddressInitializer {

    @Autowired
    private AddressRepo addressRepo;

    @PostConstruct
    private void loadAddresses(){
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("C:\\Users\\TheRudno\\Desktop\\realestate\\src\\main\\resources\\pl.json"))
        {
            Object obj = jsonParser.parse(reader);

            JSONArray addresses = (JSONArray) obj;
            List<Address> addressList = new ArrayList<>();
            //Iterate over employee array
            addresses.forEach( address -> addressList.add(parseAddressObject( (JSONObject) address )));
            if(addressRepo.findAll().size()< addressList.size()){
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
        String city= (String) address.get("city");
        String district = (String) address.get("admin");
        return new Address(country,city,district);
    }


}
