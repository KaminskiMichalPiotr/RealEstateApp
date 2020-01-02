package com.kaminski.realestateapp.announcement;

import com.kaminski.realestateapp.realestate.RealEstate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementRepo announcementRepo;

    public Optional<Announcement> findAnnouncementById(Long id) {
        return announcementRepo.findById(id);
    }

    public List<Announcement> getAnnouncements() {
        return announcementRepo.findAll();
    }

    public Announcement updateAnnouncement(Announcement announcement) {
        return announcementRepo.save(announcement);
    }

    public Announcement saveAnnouncement(Announcement announcement) {
        return announcementRepo.save(announcement);
    }

    public Announcement findAnnouncementByRealEstate(RealEstate realEstate) {
        return announcementRepo.findByRealEstate(realEstate);
    }

    public void delete(Announcement announcement) {
        announcementRepo.delete(announcement);
    }
}
