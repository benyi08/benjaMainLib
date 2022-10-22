package com.library.benjaMainLib.repository;

import com.library.benjaMainLib.model.Borrow;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BorrowRepo extends CrudRepository<Borrow, Integer> {
    List<Borrow> findAll();
    Optional<Borrow> findById(Integer id);
}
