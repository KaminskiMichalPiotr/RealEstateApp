package com.kaminski.realestateapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Optional<User> getUserByLogin(String login) {
        return Optional.ofNullable(userRepo.findByLogin(login));
    }


    public User saveUser(User user) throws UserAlreadyExistException, UserDoesntExistException {
        //update
        if (user.getId() != null) {
            Optional<User> byId = userRepo.findById(user.getId());
            if (byId.isPresent()) {
                if (user.getPassword() == null) {
                    user.setPassword(byId.get().getPassword());
                } else {
                    user = prepareUserToPersist(user);
                }
                return userRepo.save(user);
            } else {
                throw new UserDoesntExistException();
            }
        } else {
            if (userRepo.findByLogin(user.getLogin()) != null) {
                throw new UserAlreadyExistException();
            } else {
                user = prepareUserToPersist(user);
                return userRepo.save(user);
            }
        }
    }

    public User prepareUserToPersist(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return user;
    }

}
