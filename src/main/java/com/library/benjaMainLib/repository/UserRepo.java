package com.library.benjaMainLib.repository;

import com.library.benjaMainLib.model.Donor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<Donor, Integer>{
    List<Donor> findAll();
    Optional<Donor> findByUserId(Integer id);
}
