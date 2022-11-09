package com.library.benjaMainLib.service;

import com.library.benjaMainLib.model.Person;
import com.library.benjaMainLib.repository.PersonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PersonService {

    private final PersonRepo personRepo;

    public List<Person> getAllPerson(){
        return personRepo.findAll();
    }

    public Person createOnePerson(Person person){
        return personRepo.save(person);
    }

    public Optional<Person> getOnePerson(Integer id) {
        return personRepo.findById(id)
                .stream()
                .findAny();
    }

    public void delete(int id){
        if ( personRepo.findById(id).isPresent()) {
            personRepo.deleteById(id);
        }
    }

}
