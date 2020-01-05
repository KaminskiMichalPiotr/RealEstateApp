package com.kaminski.realestateapp.announcement;

import com.kaminski.realestateapp.realestate.RealEstate;
import com.kaminski.realestateapp.realestate.RealEstateDTO;
import com.kaminski.realestateapp.realestate.businessestablishment.BusinessEstablishmentDTO;
import com.kaminski.realestateapp.realestate.flat.FlatDTO;
import com.kaminski.realestateapp.realestate.house.HouseDTO;
import com.kaminski.realestateapp.realestate.plot.PlotDTO;
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

    public RealEstate mapToRealEstate(RealEstateDTO realEstate) {
        if(realEstate instanceof HouseDTO){
            return HouseDTO.adaptTo((HouseDTO) realEstate);
        }else if(realEstate instanceof FlatDTO){
            return FlatDTO.adaptTo((FlatDTO) realEstate);
        }else if(realEstate instanceof PlotDTO){
            return PlotDTO.adaptTo((PlotDTO) realEstate);
        }else if(realEstate instanceof BusinessEstablishmentDTO){
            return BusinessEstablishmentDTO.adaptTo((BusinessEstablishmentDTO) realEstate);
        }
        return null;
    }
}
