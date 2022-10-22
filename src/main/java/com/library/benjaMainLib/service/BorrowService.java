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
public class BorrowService {

    @Autowired
    BorrowRepo borrowRepo;

    @Autowired
    BookRepo bookRepo;

    public Iterable<Borrow> getAllBorrows() { return borrowRepo.findAll(); }

    public Optional<Borrow> getOneBorrow(Integer id) {
        if (borrowRepo.findById(id).isEmpty()) {
            return Optional.empty();
        } else {
            return borrowRepo.findById(id);
        }
    }

    public ResponseEntity<Borrow> removeborrow(Integer id) {
        Optional<Borrow> removableBorrow = borrowRepo.findById(id);
        if(removableBorrow.isPresent()){
            borrowRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Borrow> booking(Borrow borrow){

        Optional<Integer> bookId = getBookIdByName(borrow.getName());
        if(bookId.isPresent()){
            if(bookRepo.findById(bookId.get()).isPresent()){
                Optional<Book> possibleBook = bookRepo.findById(bookId.get());
                if (possibleBook.isPresent()){
                    possibleBook.get().setIsborrowed(true);
                    bookRepo.deleteById(bookId.get());
                    bookRepo.save(possibleBook.get());
                    return ResponseEntity.ok(borrowRepo.save(borrow));
                }
            }
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Borrow> returnBook(Integer id){

        Optional<Borrow> possibleBorrow = borrowRepo.findById(id);
        if( possibleBorrow.isPresent() ){
            Borrow borrow = possibleBorrow.get();
            if( getBookIdByName(borrow.getName()).isPresent() ){
                Integer bookId = getBookIdByName(borrow.getName()).get();
                if( bookRepo.findById(bookId).isPresent() ){
                    Book book = bookRepo.findById(bookId).get();
                    book.setIsborrowed(false);
                    bookRepo.deleteById(bookId);
                    bookRepo.save(book);
                }
            }
            borrowRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public Optional<Integer> getBookIdByName(String bookName){
        List<Book> bookList = new ArrayList<>(bookRepo.findAll());
        for(Book book : bookList){
            if (book.getTitle().equals(bookName)){
                return Optional.of(book.getId());
            }
        }
        return Optional.empty();
    }

}
