package com.kaminski.realestateapp.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;

    private String login;

    private String name;

    private String surname;

    private Boolean admin;

    private Boolean standardUser;

    public static UserDTO adaptFrom(User user) {
        return new UserDTO(user.getId(), user.getLogin(), user.getName(), user.getSurname(), user.getAdmin(), user.getStandardUser());
    }
}
