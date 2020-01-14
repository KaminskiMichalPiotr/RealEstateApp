package com.kaminski.realestateapp.initializers;

import com.kaminski.realestateapp.user.User;
import com.kaminski.realestateapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserInitializer {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void createUserIfNoneIsPresent() {
        int size = userRepo.findAll().size();
        if(size == 0){
            User admin = new User(null, "admin", passwordEncoder.encode("admin"), "Jan",
                    "Kowalski", true, true);
            userRepo.save(admin);
        }
    }

}
