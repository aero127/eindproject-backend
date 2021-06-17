package com.mtbrental.eindproject.repositories;

import com.mtbrental.eindproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
