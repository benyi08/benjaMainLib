package com.library.benjaMainLib.controller;

import com.library.benjaMainLib.model.Person;
import com.library.benjaMainLib.repository.PersonRepo;
import com.library.benjaMainLib.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonService personService;

    @GetMapping("")
    public Iterable<Person> getPerson() {
        return personService.getAllPerson();
    }

    @PostMapping("/create")
    public ResponseEntity<Person> createPerson( @RequestBody Person person) {
        return personService.createOnePerson(person);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePerson( @PathVariable Integer id ){
        return personService.delete(id);
    }

    @DeleteMapping("/deleteShow/{id}")
    public ResponseEntity<Person> deleteAndShow( @PathVariable Integer id ){
        return personService.deleteShow(id);
    }
}
