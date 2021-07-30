package nl.mtbrental.eindproject.repository;

import nl.mtbrental.eindproject.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

}

