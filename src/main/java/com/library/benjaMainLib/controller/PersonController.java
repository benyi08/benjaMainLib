package com.library.benjaMainLib.controller;

import com.library.benjaMainLib.model.Person;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PersonController {

    public Iterable<Person> getPerson();

    public ResponseEntity<Person> createPerson(@RequestBody Person person);

}
