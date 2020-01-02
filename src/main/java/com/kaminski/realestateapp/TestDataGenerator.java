package com.kaminski.realestateapp;

import com.kaminski.realestateapp.address.Address;
import com.kaminski.realestateapp.address.AddressInitializer;
import com.kaminski.realestateapp.address.AddressRepo;
import com.kaminski.realestateapp.announcement.Announcement;
import com.kaminski.realestateapp.announcement.AnnouncementRepo;
import com.kaminski.realestateapp.realestate.RealEstateRepo;
import com.kaminski.realestateapp.realestate.RealEstateType;
import com.kaminski.realestateapp.realestate.businessestablishment.BusinessEstablishment;
import com.kaminski.realestateapp.realestate.businessestablishment.BusinessEstablishmentType;
import com.kaminski.realestateapp.realestate.flat.Flat;
import com.kaminski.realestateapp.realestate.house.House;
import com.kaminski.realestateapp.realestate.plot.Plot;
import com.kaminski.realestateapp.realestate.plot.PlotType;
import com.kaminski.realestateapp.user.User;
import com.kaminski.realestateapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class TestDataGenerator {

    @Value("${spring.testMode}")
    Boolean testMode;

    @Autowired
    private RealEstateRepo estateRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private AnnouncementRepo announcementRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AddressInitializer addressInitializer;

    @PostConstruct
    private void generateTestData() {
        if (testMode) {
            cleanDataBase();
            address();
            testUsers();
            testAnnouncements();
        }
    }

    private void testAnnouncements() {
        Random rnd = new Random();
        List<Address> addresses = addressRepo.findAll();
        List<String> thumbnails = Arrays.asList(
                "https://i.picsum.photos/id/1047/160/123.jpg",
                "https://i.picsum.photos/id/1065/160/123.jpg",
                "https://i.picsum.photos/id/164/160/123.jpg",
                "https://i.picsum.photos/id/369/160/123.jpg");
        List<String> gallery = Arrays.asList(
                "https://i.picsum.photos/id/369/900/900.jpg",
                "https://i.picsum.photos/id/1047/900/900.jpg",
                "https://i.picsum.photos/id/1059/900/900.jpg",
                "https://i.picsum.photos/id/30/900/900.jpg",
                "https://i.picsum.photos/id/42/900/900.jpg",
                "https://i.picsum.photos/id/464/900/900.jpg");
        List<Announcement> announcements = new LinkedList<>();
        for (int i = 0; i < 500; i++) {
            Announcement announcement = new Announcement(null, new Date(), lorem(), gallery, null);
            if (i % 4 == 0) {
                Flat flat = new Flat(null, Math.abs(rnd.nextLong()) % 150 + 20, Math.abs(rnd.nextLong()) % 350000 + 25000,
                        null, thumbnails.get(Math.abs(rnd.nextInt()) % thumbnails.size()),
                        addresses.get(Math.abs(rnd.nextInt()) % addresses.size()), RealEstateType.FLAT,
                        Math.abs(rnd.nextInt()) % 2 == 0, Math.abs(rnd.nextInt()) % 2 == 0,
                        Math.abs(rnd.nextInt()) % 5, Math.abs(rnd.nextInt()) % 5 + 1);
                flat.setPricePerSquareMeter();
                announcement.setRealEstate(flat);
            } else if (i % 4 == 1) {
                Plot plot = new Plot(null, Math.abs(rnd.nextLong()) % 4000 + 250, Math.abs(rnd.nextLong()) % 350000 + 25000,
                        null, thumbnails.get(Math.abs(rnd.nextInt()) % thumbnails.size()),
                        addresses.get(Math.abs(rnd.nextInt()) % addresses.size()), RealEstateType.PLOT, Math.abs(rnd.nextInt()) % 2 == 0,
                        Math.abs(rnd.nextInt()) % 2 == 0, Math.abs(rnd.nextInt()) % 2 == 0,
                        Math.abs(rnd.nextInt()) % 2 == 0 ? PlotType.BUILDING_PLOT : PlotType.AGRICULTURAL_PARCEL);
                plot.setPricePerSquareMeter();
                announcement.setRealEstate(plot);
            } else if (i % 4 == 2) {
                House house = new House(null, Math.abs(rnd.nextLong()) % 400 + 50, Math.abs(rnd.nextLong()) % 1350000 + 250000,
                        null, thumbnails.get(Math.abs(rnd.nextInt()) % thumbnails.size()),
                        addresses.get(Math.abs(rnd.nextInt()) % addresses.size()), RealEstateType.HOUSE, Math.abs(rnd.nextInt()) % 2 + 1,
                        Math.abs(rnd.nextLong()) % 500 + 50, Math.abs(rnd.nextInt()) % 8 + 2);
                house.setPricePerSquareMeter();
                announcement.setRealEstate(house);
            } else {
                BusinessEstablishment businessEstablishment = new BusinessEstablishment(null,
                        Math.abs(rnd.nextLong()) % 4000 + 250, Math.abs(rnd.nextLong()) % 350000 + 25000,
                        null, thumbnails.get(Math.abs(rnd.nextInt()) % thumbnails.size()),
                        addresses.get(Math.abs(rnd.nextInt()) % addresses.size()), RealEstateType.BUSINESS_ESTABLISHMENT,
                        BusinessEstablishmentType.values()[Math.abs(rnd.nextInt()) % BusinessEstablishmentType.values().length]);
                businessEstablishment.setPricePerSquareMeter();
                announcement.setRealEstate(businessEstablishment);
            }
            announcements.add(announcement);
        }
        announcementRepo.saveAll(announcements);
    }

    private String lorem() {
        return "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore " +
                "et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut " +
                "aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum " +
                "dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui " +
                "officia deserunt mollit anim id est laborum.";
    }

    private void testUsers() {
        User admin = new User(null, "admin", passwordEncoder.encode("admin"), "Jan", "Kowalski", true, true);
        User worker = new User(null, "worker", passwordEncoder.encode("worker"), "Tomasz", "Nowak", false, true);
        userRepo.saveAll(Arrays.asList(admin, worker));
    }

    private void address() {
        addressInitializer.loadAddresses();
    }

    private void cleanDataBase() {
        userRepo.deleteAll();
        announcementRepo.deleteAll();
    }


}
