package com.kaminski.realestateapp.announcement;

import com.kaminski.realestateapp.address.Address;
import com.kaminski.realestateapp.address.AddressService;
import com.kaminski.realestateapp.realestate.RealEstate;
import com.kaminski.realestateapp.realestate.RealEstateService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/announcement", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnouncementREST {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private RealEstateService realEstateService;

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable Long id) {
        Optional<Announcement> announcement = announcementService.findAnnouncementById(id);
        if (announcement.isPresent()) {
            return ResponseEntity.ok().body(announcement.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/estate/{id}")
    @Transactional
    public ResponseEntity<AnnouncementDTO> getAnnouncementByEstateId(@PathVariable Long id) {
        Optional<RealEstate> estate = realEstateService.findById(id);
        if (estate.isPresent()) {
            Announcement announcement = announcementService.findAnnouncementByRealEstate(estate.get());
            Hibernate.initialize(announcement.getPicturesPaths());
            return ResponseEntity.ok().body(AnnouncementDTO.adaptFrom(announcement));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/estate/{id}")
    public ResponseEntity<?> deleteAnnouncementByEstateId(@PathVariable Long id) {
        Optional<RealEstate> estate = realEstateService.findById(id);
        if (estate.isPresent()) {
            Announcement announcement = announcementService.findAnnouncementByRealEstate(estate.get());
            announcementService.delete(announcement);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('WORKER')")
    public ResponseEntity<?> createAnnouncement(@RequestBody AnnouncementDTO announcement) {
        if(announcement.getId() == null){
            //create
            announcement.getRealEstate().setPricePerSquareMeter();
            announcement.getRealEstate().setId(null);
            RealEstate realEstate = announcementService.mapToRealEstate(announcement.getRealEstate());
            Address address = addressService.findAddressByCity(announcement.getRealEstate().getAddress().getCity());
            if(realEstate != null && address != null){
                realEstate.setAddress(address);
                Announcement toSave = new Announcement(announcement.getId(), new Date(), announcement.getDescription(),
                        announcement.getPicturesPaths(), realEstate);
                announcementService.saveAnnouncement(toSave);
                return ResponseEntity.ok().body(AnnouncementDTO.adaptFrom(toSave));
            }
        }else {
            //update
            if(announcement.getRealEstate().getId() != null){
                announcement.getRealEstate().setPricePerSquareMeter();
                RealEstate realEstate = announcementService.mapToRealEstate(announcement.getRealEstate());
                Address address = addressService.findAddressByCity(announcement.getRealEstate().getAddress().getCity());
                if(realEstate != null && address != null){
                    realEstate.setAddress(address);
                    Announcement toSave = new Announcement(announcement.getId(), new Date(), announcement.getDescription(),
                            announcement.getPicturesPaths(), realEstate);
                    announcementService.saveAnnouncement(toSave);
                    return ResponseEntity.ok().body(AnnouncementDTO.adaptFrom(toSave));
                }
            }
        }

        return ResponseEntity.status(409).build();
    }

    @GetMapping("")
    public ResponseEntity<List<Announcement>> getAnnouncements() {
        List<Announcement> businessEstablishments = announcementService.getAnnouncements();
        return ResponseEntity.ok().body(businessEstablishments);
    }

}
