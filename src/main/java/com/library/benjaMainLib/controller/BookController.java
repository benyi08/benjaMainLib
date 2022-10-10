package com.library.benjaMainLib.controller;

import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    @GetMapping("")
    public Iterable<Book> getBookList(){
        return bookService.getAllBooks();
    }

    @PostMapping("/create")
    public ResponseEntity<Book> registerBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBook(@PathVariable Integer id){
        return bookService.deleteBook(id);
    }

    @DeleteMapping("/deleteshow/{id}")
    public ResponseEntity<Book> deleteAndShow(@PathVariable Integer id){
        return bookService.deleteAndShow(id);
    }

}
