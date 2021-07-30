package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.exceptions.NotFoundException;
import nl.mtbrental.eindproject.exceptions.RecordNotFoundException;
import nl.mtbrental.eindproject.exceptions.UsernameNotFoundException;
import nl.mtbrental.eindproject.model.Authority;
import nl.mtbrental.eindproject.model.User;
import nl.mtbrental.eindproject.repository.UserRepository;
import nl.mtbrental.eindproject.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private AuthorityRepository authorityRepository;

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


//    public User getUserById(Long id) {
//        return userRepository.findById(id);
//    }

    @Override
    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    @Override
    public String createUser(User user) {
        String randomString = RandomStringGenerator.generateAlphaNumeric(20);
        user.setApikey(randomString);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        return newUser.getUsername();
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteById(username);
    }

    @Override
    public void updateUser(String username, User newUser) {
        if (!userRepository.existsById(username)) throw new RecordNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    @Override
    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        return user.getAuthorities();
    }

    @Override
    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    @Override
    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UsernameNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

    @Override
    public void uploadIdentification(String username, MultipartFile file) throws IOException {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            user.setIdentification(file.getBytes());
            userRepository.save(user);
        } else {
            throw new NotFoundException();
        }
    }

    @Override
    public byte[] getIdentification(String username) {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            return optionalUser.get().getIdentification();
        } else {
            throw new NotFoundException();
        }
    }

}