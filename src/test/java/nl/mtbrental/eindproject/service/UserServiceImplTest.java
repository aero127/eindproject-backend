package nl.mtbrental.eindproject.service;

import nl.mtbrental.eindproject.exceptions.RecordNotFoundException;
import nl.mtbrental.eindproject.model.Authority;
import nl.mtbrental.eindproject.model.User;
import nl.mtbrental.eindproject.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {


    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Captor
    ArgumentCaptor<User> userCaptor;

    @Test
    public void getAllUsersTest() {
        when(userRepository.findAll()).thenReturn(List.of(new User(), new User()));
        List<User> userList = userServiceImpl.getUsers();
        assertEquals(2, userList.size());
    }

    @Test
    public void deleteUserTest() {
        User user = new User();
        user.setUsername("Arnold");
        userServiceImpl.deleteUser(user.getUsername());
        verify(userRepository).deleteById(user.getUsername());
    }

    @Test
    public void getUserTest() {
        User user = new User();
        user.setUsername("Arnold");
        String username = user.getUsername();
        when(userRepository.findById(username)).thenReturn(Optional.of(user));
        Optional<User> userOptional = userServiceImpl.getUser(username);
        assertTrue(userOptional.isPresent());
        assertEquals(username, userOptional.get().getUsername());
    }

    @Test
    public void userExistsTest() {
        User user = new User();
        user.setUsername("Arnold");
        String username = user.getUsername();
        when(userRepository.existsById(username)).thenReturn(true);
        assertTrue(userRepository.existsById(username));
    }

    @Test
    public void updateUserTest() {
        User initialUser = new User();
        initialUser.setUsername("Arnold");
        initialUser.setPassword("abcd1234");
        User update = new User();
        update.setUsername("Frits");
        update.setPassword("password");
        when(userRepository.existsById(update.getUsername())).thenReturn(true);
        when(userRepository.findById(update.getUsername())).thenReturn(Optional.of(initialUser));
        userServiceImpl.updateUser(update.getUsername(), update);
        verify(userRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertThat(savedUser.getPassword().equals(update.getPassword()));
    }

    @Test
    public void createUserTest() {
        User initialUser = new User();
        initialUser.setUsername("Arnold");
        initialUser.setPassword(passwordEncoder.encode("abcd1234"));
        String passwordForm = passwordEncoder.encode(initialUser.getPassword());
        when(userRepository.save(initialUser)).thenReturn(initialUser);
        userServiceImpl.createUser(initialUser);
        verify(userRepository).save(userCaptor.capture());
        User newUser = userCaptor.getValue();
        assertThat(initialUser.getUsername().equals(newUser.getUsername()));
    }

    @Test
    public void getAuthorityTest() {
        User testUser = new User();
        testUser.setUsername("username");
        testUser.addAuthority(new Authority(testUser.getUsername(), "ADMIN"));
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        when(userRepository.findById(testUser.getUsername())).thenReturn(java.util.Optional.of(testUser));
        assertEquals(testUser.getAuthorities(), userServiceImpl.getAuthorities(testUser.getUsername()));
    }

    @Test
    public void removeAuthorityTest() {
        User testUser = new User();
        testUser.setUsername("username");
        testUser.addAuthority(new Authority(testUser.getUsername(), "ADMIN"));
        when(userRepository.existsById(testUser.getUsername())).thenReturn(true);
        when(userRepository.findById(testUser.getUsername())).thenReturn(java.util.Optional.of(testUser));
        userServiceImpl.removeAuthority(testUser.getUsername(), "ADMIN");
        verify(userRepository).save(testUser);
    }


    @Test
    public void updateUserTest2(){
        User testUser = new User();
        testUser.setUsername("username");
        User testUser2 = new User();
        testUser2.setUsername("freekwillem");
        when(userRepository.existsById(testUser.getUsername())).thenReturn(false);
        assertThrows(RecordNotFoundException.class, ()-> {
            userServiceImpl.updateUser(testUser.getUsername(), testUser2);
        });
    }
}