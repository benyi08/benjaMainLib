package com.library.benjaMainLib.repository;

import com.library.benjaMainLib.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends CrudRepository<Book, Integer> {
    List<Book> findAll();
    Optional<Book> findById(Integer id);
}
