package nl.mtbrental.eindproject.dto;

import nl.mtbrental.eindproject.model.User;

public class UserInputDto {
    public String username;
    public String firstName;
    public String lastName;
    public String email;

    public User toUser() {
        var user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }
}
