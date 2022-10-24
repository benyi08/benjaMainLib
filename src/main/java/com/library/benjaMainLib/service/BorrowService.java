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
import java.time.LocalDateTime;

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

    public ResponseEntity<Borrow> booking(Borrow borrow){

        Optional<Integer> bookId = getBookIdByName(borrow.getTitle());
        if(bookId.isPresent() && ! bookRepo.findById(bookId.get()).get().isIsborrowed() ){
            Book book = bookRepo.findById(bookId.get()).get();
            book.setIsborrowed(true);
            bookRepo.save(book);

            borrow.setBorrowdate(LocalDateTime.now());
            return ResponseEntity.ok(borrowRepo.save(borrow));
        }
        return ResponseEntity.badRequest().build();
    }

    public ResponseEntity<Borrow> returnBook(Integer id){

        Optional<Borrow> possibleBorrow = borrowRepo.findById(id);
        if( possibleBorrow.isPresent() ){
            Book book = bookRepo.findById(getBookIdByName(possibleBorrow.get().getTitle()).get()).get();
            book.setIsborrowed(false);
            bookRepo.save(book);

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
