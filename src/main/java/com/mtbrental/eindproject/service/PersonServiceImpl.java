package com.mtbrental.eindproject.service;

import com.mtbrental.eindproject.model.Person;
import com.mtbrental.eindproject.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    public List<Person> getPersons(){
        return personRepository.findAll();
    }

    public Person getPerson(long id){
        return personRepository.getById(id);
    }

    public Person addPerson(Person person){
/*        Optional<Person> personOptional = personRepository.findPersonByEmailAdress(person.getEmailAdress());
        if (personOptional.isPresent()) {
            throw new IllegalStateException("email al in gebruik");
        }*/
        return personRepository.save(person);
    }

    public void removePerson(long id){
        personRepository.deleteById(id);
    }

    public void updatePerson(long id, Person person){}

}
