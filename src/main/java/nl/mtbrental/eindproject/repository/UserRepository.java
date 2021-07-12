package nl.mtbrental.eindproject.repository;

import nl.mtbrental.eindproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

}
