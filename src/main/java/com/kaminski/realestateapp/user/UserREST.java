package com.kaminski.realestateapp.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserREST {

    @Autowired
    UserRepo userRepo;

    @Autowired
    private UserService userService;


    @Transactional
    @GetMapping(value = "/current")
    @ResponseBody
    public ResponseEntity<UserDTO> currentUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        if (principal != null) {
            User byLogin = userRepo.findByLogin(principal.getName());
            if (byLogin != null) {
                return ResponseEntity.ok(UserDTO.adaptFrom(byLogin));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> userDTOS = userRepo.findAll().stream().map(UserDTO::adaptFrom).collect(Collectors.toList());
        return ResponseEntity.ok().body(userDTOS);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> saveUser(@RequestBody User user) {
        User saved = null;
        try {
            saved = userService.saveUser(user);
            return ResponseEntity.ok().body(UserDTO.adaptFrom(saved));
        } catch (UserDoesntExistException | UserAlreadyExistException e) {
            return ResponseEntity.status(409).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        if (userRepo.findById(id).isPresent()) {
            userRepo.deleteById(id);
            return ResponseEntity.accepted().build();
        }
        return ResponseEntity.notFound().build();
    }


}
