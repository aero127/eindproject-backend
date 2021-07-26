package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.model.Authority;
import nl.mtbrental.eindproject.model.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    public abstract String createUser(User user);
    public abstract void updateUser(String username, User user);
    public abstract void deleteUser(String username);
    public abstract List<User> getUsers();
//    public abstract User getUserById(Long id);
    public abstract Optional<User> getUser(String username);
    public abstract boolean userExists(String username);
    public abstract Set<Authority> getAuthorities(String username);
    void addAuthority(String username, String authority);
    void removeAuthority(String username, String authority);

    void uploadIdentification(String username, MultipartFile file) throws IOException;

    byte[] getIdentification(String username);
}