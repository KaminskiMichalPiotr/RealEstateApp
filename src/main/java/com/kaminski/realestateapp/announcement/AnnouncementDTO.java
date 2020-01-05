package com.kaminski.realestateapp.announcement;

import com.kaminski.realestateapp.realestate.RealEstateDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AnnouncementDTO {

    private Long id;
    private Date announcementDate;
    private String description;
    private List<String> picturesPaths;
    private RealEstateDTO realEstate;

    public static AnnouncementDTO adaptFrom(Announcement announcement) {
        return new AnnouncementDTO(announcement.getId(), announcement.getAnnouncementDate(), announcement.getDescription(),
                announcement.getPicturesPaths(), RealEstateDTO.adaptFrom(announcement.getRealEstate()));
    }
}
