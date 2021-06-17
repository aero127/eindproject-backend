package com.mtbrental.eindproject.service;

import com.mtbrental.eindproject.model.Person;

import java.util.List;

public interface PersonService {

    List<Person> getPersons();
    Person getPerson(long id);
    Person addPerson(Person person);
    void removePerson(long id);
    void updatePerson(long id, Person person);

}
