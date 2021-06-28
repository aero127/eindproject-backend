package com.mtbrental.eindproject.repositories;

import com.mtbrental.eindproject.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person, Long> {
/*

    Optional<Person> findPersonByEmailAdress(String email);*/
}
