package com.library.benjaMainLib.controller;

import com.library.benjaMainLib.controller.model.BookDetailView;
import com.library.benjaMainLib.controller.model.BorrowSummaryView;
import com.library.benjaMainLib.controller.model.PersonDetailView;
import com.library.benjaMainLib.controller.transformer.BookTransformer;
import com.library.benjaMainLib.controller.transformer.BorrowTransformer;
import com.library.benjaMainLib.controller.transformer.PersonTransformer;
import com.library.benjaMainLib.model.Book;
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
@RequestMapping("/librarian")
public class LibrarianController {

    private final PersonService personService;
    private final BookService bookService;
    private final BorrowService borrowService;
    private final PersonTransformer personTransformer;
    private final BookTransformer bookTransformer;
    private final BorrowTransformer borrowTransformer;

    //PEOPLE REQUESTS
    @GetMapping("/people/all")
    public List<PersonDetailView> getAllPerson(){
        return personService.getAllPerson()
                .stream()
                .map(personTransformer::transformDetailView) //person -> personTransformer.transformDetailView(person)
                .collect(Collectors.toList());
    }

    @GetMapping("/people/{id}")
    public ResponseEntity<PersonDetailView> getPersonById(@PathVariable Integer id) {
        Optional<Person> optionalPerson = personService.getOnePerson(id);
        if(optionalPerson.isPresent()){
            Person person = optionalPerson.get();
            PersonDetailView personDetailView = personTransformer.transformDetailView(person);
            return ResponseEntity.ok(personDetailView);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/people/create")
    public ResponseEntity<PersonDetailView> createPerson(@RequestBody Person person) {
        Person createdPerson = personService.createOnePerson(person);
        return ResponseEntity.ok(personTransformer.transformDetailView(createdPerson));
    }

    @DeleteMapping("/people/delete/{id}")
    public void deletePerson( @PathVariable Integer id ){ personService.delete(id); }

    //BOOK REQUESTS
    @GetMapping("/book/all")
    public List<BookDetailView> getBookList(){
        return bookService.getAllBooks()
                .stream()
                .map(bookTransformer::transformBookDetailView)
                .collect(Collectors.toList());
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDetailView> getBookById(@PathVariable Integer id){
        Optional<Book> optionalBook = bookService.getOneBook(id);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            BookDetailView bookDetailView = bookTransformer.transformBookDetailView(book);
            return ResponseEntity.ok(bookDetailView);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/book/create")
    public ResponseEntity<BookDetailView> createBook(@RequestBody Book book) {
        bookService.createBook(book);
        return ResponseEntity.ok(bookTransformer.transformBookDetailView(book));
    }

    @DeleteMapping("/book/delete/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer id){ return bookService.deleteBook(id); }

    @PutMapping("/book/edit/{id}")
    public ResponseEntity<Book> editBook(@PathVariable Integer id, @RequestBody Book book){ return bookService.updateBook(id, book);}

    //BORROW REQUESTS
    @GetMapping("/borrows")
    public List<BorrowSummaryView> getBorrowList() {
        return borrowService.getAllBorrows()
                .stream()
                .map(borrowTransformer::transformBorrowSummaryView)
                .collect(Collectors.toList());
    }

}
