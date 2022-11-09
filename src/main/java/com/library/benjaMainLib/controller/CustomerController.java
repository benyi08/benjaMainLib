package com.library.benjaMainLib.controller;

import com.library.benjaMainLib.controller.model.*;
import com.library.benjaMainLib.controller.transformer.BookTransformer;
import com.library.benjaMainLib.controller.transformer.BorrowTransformer;
import com.library.benjaMainLib.controller.transformer.PersonTransformer;
import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.model.Borrow;
import com.library.benjaMainLib.model.Person;
import com.library.benjaMainLib.service.BookService;
import com.library.benjaMainLib.service.BorrowService;
import com.library.benjaMainLib.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class CustomerController {
    private final PersonService personService;
    private final BookService bookService;
    private final BorrowService borrowService;
    private final PersonTransformer personTransformer;
    private final BookTransformer bookTransformer;
    private final BorrowTransformer borrowTransformer;

    //PERSON REQUESTS
    @GetMapping("/people/all")
    public List<PersonSummaryView> getPersons() {
        return personService.getAllPerson()
                .stream()
                .map(personTransformer::transformSummaryView)
                .collect(Collectors.toList());
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<PersonDetailView> getPerson(@PathVariable int id) {
        Optional<Person> optionalPerson = personService.getOnePerson(id);
        if (optionalPerson.isPresent()) {
            Person person = optionalPerson.get();
            PersonDetailView personDetailView = personTransformer.transformDetailView(person);
            return ResponseEntity.ok(personDetailView);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/people/create")
    public ResponseEntity<PersonDetailView> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createOnePerson(person);
        return ResponseEntity.ok(personTransformer.transformDetailView(createdPerson));
    }

    //BOOK REQUESTS
    @GetMapping("/availablebooks/all")
    public List<BookSummaryView> getAvailableBookList() {
        return bookService.getAllAvailableBooks()
                .stream()
                .map(bookTransformer::transformBookSummaryView)
                .collect(Collectors.toList());
    }

    @GetMapping("/availablebooks/{id}")
    public ResponseEntity<BookDetailView> getBookById(@PathVariable Integer id) {
        Optional<Book> optionalBook = bookService.getAvailableBook(id);
        if(optionalBook.isPresent()){
            Book book = optionalBook.get();
            BookDetailView bookDetailView = bookTransformer.transformBookDetailView(book);
            return ResponseEntity.ok(bookDetailView);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    //BORROW REQUESTS
    @PostMapping("/borrow")
    public ResponseEntity<BorrowDetailView> bookBorrow(@RequestBody Borrow borrow) {
        if( borrowService.booking(borrow).isPresent() ){
            Borrow createdBorrow = borrowService.booking(borrow).get();
            return ResponseEntity.ok(borrowTransformer.transformBorrowDetailView(createdBorrow));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/return/{id}")
    public ResponseEntity<Borrow> bookReturn(@PathVariable Integer id) {
        return borrowService.withDrawBorrow(id);
    }
}

