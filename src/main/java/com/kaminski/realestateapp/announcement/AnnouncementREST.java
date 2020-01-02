package com.kaminski.realestateapp.announcement;

import com.kaminski.realestateapp.realestate.RealEstate;
import com.kaminski.realestateapp.realestate.RealEstateService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/announcement", produces = MediaType.APPLICATION_JSON_VALUE)
public class AnnouncementREST {

    @Autowired
    private AnnouncementService announcementService;

    @Autowired
    private RealEstateService realEstateService;

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
        if(estate.isPresent()) {
            Announcement announcement = announcementService.findAnnouncementByRealEstate(estate.get());
            Hibernate.initialize(announcement.getPicturesPaths());
            return ResponseEntity.ok().body(AnnouncementDTO.adaptFrom(announcement));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/estate/{id}")
    public ResponseEntity<?> deleteAnnouncementByEstateId(@PathVariable Long id) {
        Optional<RealEstate> estate = realEstateService.findById(id);
        if(estate.isPresent()) {
            Announcement announcement = announcementService.findAnnouncementByRealEstate(estate.get());
            announcementService.delete(announcement);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('WORKER')")
    public ResponseEntity<Announcement> createAnnouncement(@RequestBody Announcement announcement) {
        if (announcementService.findAnnouncementById(announcement.getId()).isPresent()) {
            announcement = announcementService.updateAnnouncement(announcement);
        } else {
            announcement.setId(null);
            announcement = announcementService.saveAnnouncement(announcement);
        }
        return ResponseEntity.ok().body(announcement);
    }

    @GetMapping("")
    public ResponseEntity<List<Announcement>> getAnnouncements() {
        List<Announcement> businessEstablishments = announcementService.getAnnouncements();
        return ResponseEntity.ok().body(businessEstablishments);
    }

}
