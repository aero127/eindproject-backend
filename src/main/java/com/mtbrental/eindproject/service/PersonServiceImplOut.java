/*
package com.mtbrental.eindproject.service;

import com.mtbrental.eindproject.exception.RecordNotFoundException;
import com.mtbrental.eindproject.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

*/
/*@Service*//*

public class PersonServiceImplOut  {

    private List<Person> persons = new ArrayList<>();

    @Override
    public List<Person> getPersons() { return persons; }

    @Override
    public Person getPerson(long id) {
        Person person = null;
        for (int i = 0; i < persons.size(); i++) {
          if (id == persons.get(i).getId()) {
              person = persons.get(i);
          }
        }
        if (person == null) {
            throw new RecordNotFoundException("id bestaat niet");
        }
        return person;
    }

    @Override
    public Person addPerson(Person person) {
        persons.add(person);
        return person;
    }

    @Override
    public void removePerson(long id) {
        Person person = null;
        for (int i = 0; i < persons.size(); i++) {
           if (id == persons.get(i).getId()) {
               persons.remove(i);
           }
        }
        if (person == null) {
            throw new RecordNotFoundException("id bestaat niet");
        }
    }

    @Override
    public void updatePerson(long id, Person newPerson) {
        Person person = null;
        for (int i = 0; i <persons.size() ; i++) {
            if (id == persons.get(i).getId()) {
                person = persons.get(i);
            }
        }
        if (person == null) {
            throw new RecordNotFoundException("id bestaat niet");
        }
        else {
            person.setFirstName(newPerson.getFirstName());
            person.setLastName(newPerson.getLastName());
            person.setEmailAdress(newPerson.getEmailAdress());
            person.setPassword(newPerson.getPassword());
        }
    }
}
*/
