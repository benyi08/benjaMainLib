package com.library.benjaMainLib.controller;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.model.Borrow;
import com.library.benjaMainLib.model.Person;
import com.library.benjaMainLib.service.BookService;
import com.library.benjaMainLib.service.BorrowService;
import com.library.benjaMainLib.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/librarian")
public class LibrarianController implements PersonController, BookController, BorrowController {

    @Autowired
    PersonService personService;

    @Autowired
    BookService bookService;

    @Autowired
    BorrowService borrowService;

    //PEOPLE REQUESTS
    @GetMapping("/people/all")
    public Iterable<Person> getPerson(){ return personService.getAllPerson(); }

    @GetMapping("/people/{id}")
    public Optional<Person> getPersonById(@PathVariable Integer id) { return personService.getOnePerson(id); }

    @PostMapping("/people/create")
    public ResponseEntity<Person> createPerson(@RequestBody Person person) { return personService.createOnePerson(person); }

    @DeleteMapping("/people/delete/{id}")
    public void deletePerson( @PathVariable Integer id ){ personService.delete(id); }

    //BOOK REQUESTS
    @GetMapping("/book/all")
    public Iterable<Book> getBookList(){ return bookService.getAllBooks(); }

    @GetMapping("/book/{id}")
    public Optional<Book> getBookById(@PathVariable Integer id){ return bookService.getOneBook(id); }

    @PostMapping("/book/create")
    public ResponseEntity<Book> createBook(@RequestBody Book book) { return bookService.createBook(book); }

    @DeleteMapping("/book/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer id){ return bookService.deleteBook(id); }

    @PutMapping("/book/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Integer id, @RequestBody Book book){ return bookService.updateBook(id, book);}

    //BORROW REQUESTS
    @GetMapping("/borrows")
    public Iterable<Borrow> getBorrowList() { return borrowService.getAllBorrows(); }

}
