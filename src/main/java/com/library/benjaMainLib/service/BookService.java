package com.library.benjaMainLib.service;

import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.repository.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    public Iterable<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public ResponseEntity<Book> createBook(Book book) {
        return ResponseEntity.ok(bookRepo.save(book));
    }

    public ResponseEntity deleteBook(Integer id) {
        Optional<Book> bookTmp = bookRepo.findById(id);
        if (bookTmp.isEmpty()){
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity<Book> deleteAndShow(Integer id) {
        Optional<Book> bookTmp = bookRepo.findById(id);
        if(!bookTmp.isEmpty()){
            bookRepo.deleteById(id);
            return ResponseEntity.ok(bookTmp.get());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
