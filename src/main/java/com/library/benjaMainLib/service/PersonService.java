package com.library.benjaMainLib.service;

import com.library.benjaMainLib.model.Person;
import com.library.benjaMainLib.repository.PersonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    PersonRepo personRepo;

    public Iterable<Person> getAllPerson(){ return personRepo.findAll(); }

    public ResponseEntity<Person> createOnePerson(Person person){
        return ResponseEntity.ok(personRepo.save(person));
    }

    public Optional<Person> getOnePerson(Integer id) {
        Optional<Person> optionalPersonTmp = personRepo.findById(id);
        if(optionalPersonTmp.isPresent()) {
            return personRepo.findById(id);
        } else {
            return Optional.empty();
        }
    }

    public void delete(int id){
        Optional<Person> optionalPersonTmp = personRepo.findById(id);
        if (optionalPersonTmp.isPresent()) {
            personRepo.deleteById(id);
        }
    }

    public ResponseEntity<Person> deleteShow(int id){
        Optional<Person> tmp = personRepo.findById(id);
        if(tmp.isEmpty()){
            return ResponseEntity.badRequest().build();
        } else {
            personRepo.deleteById(id);
            return ResponseEntity.ok(tmp.get());
        }
    }

}
