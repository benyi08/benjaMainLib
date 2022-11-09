package com.library.benjaMainLib.service;

import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.model.Borrow;
import com.library.benjaMainLib.repository.BookRepo;
import com.library.benjaMainLib.repository.BorrowRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRepo borrowRepo;
    private final BookRepo bookRepo;

    public List<Borrow> getAllBorrows() {
        return borrowRepo.findAll();
    }

    public Optional<Borrow> booking(Borrow borrow){
        Optional<Book> possibleBook = getBookByName(borrow.getTitle());
        if(possibleBook.isPresent() && ! possibleBook.get().isIsborrowed() ){
            Book book = possibleBook.get();
            book.setIsborrowed(true);
            bookRepo.save(book);

            borrow.setBorrowdate(LocalDateTime.now());
            sendNotificationWhenBookOverdue();
            borrowRepo.save(borrow);
            return Optional.of(borrow);
        } else {
            return Optional.empty();
        }
    }

    private final int twoWeeksInSeconds = 1209600;

    @Scheduled(fixedRate = twoWeeksInSeconds)
    public void sendNotificationWhenBookOverdue(){
        Logger logger = Logger.getLogger(BorrowService.class.getName());
        logger.log(Level.WARNING, "The 3 week of borrow limit is expired. Please return the borrowed book asap!");
    }

    public ResponseEntity<Borrow> withDrawBorrow(Integer id){
        Optional<Borrow> possibleBorrow = borrowRepo.findById(id);
        if( possibleBorrow.isPresent() ){
            Book book = getBookByName(possibleBorrow.get().getTitle()).get();
            book.setIsborrowed(false);
            bookRepo.save(book);

            borrowRepo.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public Optional<Book> getBookByName(String bookName){
        return bookRepo.findAll()
                .stream()
                .filter(book -> book.getTitle().equals(bookName))
                .findFirst();
    }


}
