package com.library.benjaMainLib.repository;

import com.library.benjaMainLib.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepo extends CrudRepository<Person, Integer>{
    List<Person> findAll();
    Optional<Person> findById(Integer id);
}
