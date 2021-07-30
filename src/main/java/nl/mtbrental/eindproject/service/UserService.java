package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.model.Authority;
import nl.mtbrental.eindproject.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    String createUser(User user);
    void updateUser(String username, User user);
    void deleteUser(String username);
    List<User> getUsers();
    Optional<User> getUser(String username);
    boolean userExists(String username);
    Set<Authority> getAuthorities(String username);
    void addAuthority(String username, String authority);
    void removeAuthority(String username, String authority);

    void uploadIdentification(String username, MultipartFile file) throws IOException;

    byte[] getIdentification(String username);
}