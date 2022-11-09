package com.library.benjaMainLib.service;

import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.repository.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepo bookRepo;

    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public List<Book> getAllAvailableBooks(){
        return bookRepo.findAll()
                .stream()
                .filter(book -> ! book.isIsborrowed())
                .collect(Collectors.toList());
    }

    public Optional<Book> getAvailableBook(Integer id){
        return bookRepo.findById(id)
                .stream()
                .filter(book -> ! book.isIsborrowed())
                .findAny();
    }

    public Optional<Book> getOneBook(Integer id) {
        return bookRepo.findById(id)
                .stream()
                .findAny();
    }

    public void createBook(Book book) {
        bookRepo.save(book);
    }

    public ResponseEntity<Book> deleteBook(Integer id) {
        if (bookRepo.findById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            bookRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity<Book> updateBook(Integer id, Book book) {
        Optional<Book> bookWaitingForUpdate = bookRepo.findById(id);
        if( bookWaitingForUpdate.isPresent() ){
            Book modifiedBook = bookWaitingForUpdate.get();
            modifiedBook.setAuthor(book.getAuthor());
            modifiedBook.setTitle(book.getTitle());
            modifiedBook.setPages(book.getPages());
            modifiedBook.setPublishdate(book.getPublishdate());
            modifiedBook.setIsborrowed(book.isIsborrowed());
            bookRepo.save(modifiedBook);
            return ResponseEntity.ok(modifiedBook);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
