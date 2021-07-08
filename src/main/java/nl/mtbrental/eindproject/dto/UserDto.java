package nl.mtbrental.eindproject.dto;

import nl.mtbrental.eindproject.model.User;

public class UserDto {
    public String username;
    public String firstName;
    public String lastName;
    public String email;

    public static UserDto fromUser(User user) {
        var dto = new UserDto();
        dto.username = user.getUsername();
        dto.firstName = user.getFirstName();
        dto.lastName = user.getLastName();
        dto.email = user.getEmail();
        return dto;
    }
}
