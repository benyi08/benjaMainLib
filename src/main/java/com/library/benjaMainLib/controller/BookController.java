package com.library.benjaMainLib.controller;

import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

public interface BookController {

    public Iterable<Book> getBookList();

    public Optional<Book> getBookById(@PathVariable Integer id);
}
