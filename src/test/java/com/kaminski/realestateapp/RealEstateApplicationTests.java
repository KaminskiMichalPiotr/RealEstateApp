package com.kaminski.realestateapp;

import com.kaminski.realestateapp.address.Address;
import com.kaminski.realestateapp.address.AddressRepo;
import com.kaminski.realestateapp.announcement.Announcement;
import com.kaminski.realestateapp.announcement.AnnouncementRepo;
import com.kaminski.realestateapp.realestate.RealEstateType;
import com.kaminski.realestateapp.realestate.businessestablishment.BusinessEstablishment;
import com.kaminski.realestateapp.realestate.businessestablishment.BusinessEstablishmentType;
import com.kaminski.realestateapp.user.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class RealEstateApplicationTests {

	@Autowired
	UserRepo userRepo;

	@Autowired
	AddressRepo addressRepo;

	@Autowired
	AnnouncementRepo announcementRepo;

	@Test
	void contextLoads() {
	}

	@Test
	void atLestOneUserIsPresent(){
		assertTrue(userRepo.findAll().size() > 0);
	}

	@Test
	void announcementIsAdded(){
		Address address = addressRepo.findAll().get(0);
		BusinessEstablishment businessEstablishment = new BusinessEstablishment(null,5L,50L,5L,
				"",address, RealEstateType.BUSINESS_ESTABLISHMENT, BusinessEstablishmentType.GARAGE);
		Announcement announcement = new Announcement(null, new Date(), "null",
				Collections.emptyList(), businessEstablishment);
		Announcement saved = announcementRepo.save(announcement);
		Optional<Announcement> byId = announcementRepo.findById(saved.getId());
		assertTrue(byId.isPresent());
		announcementRepo.delete(saved);
		byId = announcementRepo.findById(saved.getId());
		assertFalse(byId.isPresent());
	}

}
