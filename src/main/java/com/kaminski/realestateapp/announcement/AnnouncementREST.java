package com.kaminski.realestateapp.announcement;

import com.kaminski.realestateapp.realestate.businessestablishment.BusinessEstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/announcement", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class AnnouncementREST {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/{id}")
    public ResponseEntity<Announcement> getAnnouncementById(@PathVariable Long id) {
        Optional<Announcement> announcement = announcementService.findAnnouncementById(id);
        if (announcement.isPresent()) {
            return ResponseEntity.ok().body(announcement.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
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
