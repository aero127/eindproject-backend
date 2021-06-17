package com.mtbrental.eindproject.controller;


import com.mtbrental.eindproject.model.Person;
import com.mtbrental.eindproject.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping ("")
    public ResponseEntity<Object> getPersons() {return ResponseEntity.ok(personService.getPersons());}

    @PostMapping("")
    public ResponseEntity<Object> addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        return ResponseEntity.ok("added");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPersons(@PathVariable("id") long id) {
        Person person = personService.getPerson(id);
        return ResponseEntity.ok(person);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePerson(@PathVariable("id") long id, @RequestBody Person newPerson) {
        personService.updatePerson(id, newPerson);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> removePerson(@PathVariable("id") long id) {
        personService.removePerson(id);
        return ResponseEntity.noContent().build();
    }
}
