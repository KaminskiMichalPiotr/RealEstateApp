package com.kaminski.realestateapp.configuration;

import com.kaminski.realestateapp.user.Role;
import com.kaminski.realestateapp.user.User;
import com.kaminski.realestateapp.user.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepo.findByLogin(s);

        if(user == null) {
            throw new UsernameNotFoundException(String.format("The username %s doesn't exist", s));
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        if(user.getAdmin()) {
            authorities.add(new SimpleGrantedAuthority(Role.ADMIN.toString()));
        }
        if(user.getStandardUser()){
            authorities.add(new SimpleGrantedAuthority(Role.WORKER.toString()));
        }

        UserDetails userDetails = new org.springframework.security.core.userdetails.
                User(user.getLogin(), user.getPassword(), authorities);

        return userDetails;
    }

}
