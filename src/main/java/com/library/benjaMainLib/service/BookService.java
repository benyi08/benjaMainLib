package com.library.benjaMainLib.service;

import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.model.Borrow;
import com.library.benjaMainLib.repository.BookRepo;
import com.library.benjaMainLib.repository.BorrowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepo bookRepo;

    @Autowired
    BorrowRepo borrowRepo;

    public Iterable<Book> getAllBooks(){
        return bookRepo.findAll();
    }

    public List<Book> getAllAvailableBooks(){
        Iterable<Book> allBooksList = bookRepo.findAll();
        List<Book> availableBooksList = new ArrayList<>();
        for(Book book : allBooksList){
            if( ! book.isIsborrowed() ){
                availableBooksList.add(book);
            }
        }
        return availableBooksList;
    }

    public Optional<Book> getAvailableBook(Integer id){
        Optional<Book> possibleBook = bookRepo.findById(id);
        if( possibleBook.isPresent()){
            Book book = possibleBook.get();
            if( ! book.isIsborrowed()){
                return possibleBook;
            } else {
                return Optional.empty();
            }
        } else {
            return Optional.empty();
        }
    }

    public Optional<Book> getOneBook(Integer id) {
        Optional<Book> bookTmp = bookRepo.findById(id);
        if (bookTmp.isEmpty()){
            return Optional.empty();
        } else {
            return bookRepo.findById(id);
        }
    }

    public ResponseEntity<Book> createBook(Book book) {
        return ResponseEntity.ok(bookRepo.save(book));
    }

    public ResponseEntity<Book> deleteBook(Integer id) {
        Optional<Book> bookTmp = bookRepo.findById(id);
        if (bookTmp.isEmpty()){
            return ResponseEntity.badRequest().build();
        } else {
            bookRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
    }

    public ResponseEntity<Book> deleteAndShow(Integer id) {
        Optional<Book> bookTmp = bookRepo.findById(id);
        if(bookTmp.isEmpty()){
            return ResponseEntity.badRequest().build();
        } else {
            bookRepo.deleteById(id);
            return ResponseEntity.ok(bookTmp.get());
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
            return ResponseEntity.ok(bookRepo.save(modifiedBook));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
