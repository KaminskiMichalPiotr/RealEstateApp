package com.kaminski.realestateapp.realestate.businessestablishment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusinessEstablishmentRepo extends JpaRepository<BusinessEstablishment, Long> {
}
