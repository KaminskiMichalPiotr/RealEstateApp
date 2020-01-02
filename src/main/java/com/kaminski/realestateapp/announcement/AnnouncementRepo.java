package com.kaminski.realestateapp.announcement;

import com.kaminski.realestateapp.realestate.RealEstate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncementRepo extends JpaRepository<Announcement, Long> {


    Announcement findByRealEstate(RealEstate realEstate);

}
