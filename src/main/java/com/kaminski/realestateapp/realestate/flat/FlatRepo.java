package com.kaminski.realestateapp.realestate.flat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepo extends JpaRepository<Flat, Long> {

}
