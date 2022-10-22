package com.library.benjaMainLib.controller;

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
@RequestMapping("/customer")
public class CustomerController implements BookController, PersonController {

    @Autowired
    PersonService personService;

    @Autowired
    BookService bookService;

    @Autowired
    BorrowService borrowService;

    //PERSON REQUESTS
    @GetMapping("/people/all")
    public Iterable<Person> getPerson() { return personService.getAllPerson(); }

    @GetMapping("/people/create")
    public ResponseEntity<Person> createPerson(@RequestBody Person person){
        return  personService.createOnePerson(person);
    }

    //BOOK REQUESTS
    @GetMapping("/availablebooks/all")
    public Iterable<Book> getBookList(){
        return bookService.getAllAvailableBooks();
    }

    @GetMapping("/availablebooks/{id}")
    public Optional<Book> getBookById(@PathVariable Integer id){ return bookService.getAvailableBook(id); }


    //BORROW REQUESTS
    @PostMapping("/borrow")
    public ResponseEntity<Borrow> bookBorrow(@RequestBody Borrow borrow){
        return borrowService.booking(borrow);
    }

    @DeleteMapping("/return")
    public ResponseEntity<Borrow> bookReturn(@PathVariable Integer id){
        return borrowService.returnBook(id);
    }
}
