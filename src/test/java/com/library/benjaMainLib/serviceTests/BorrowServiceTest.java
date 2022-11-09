package com.library.benjaMainLib.serviceTests;

import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.model.Borrow;
import com.library.benjaMainLib.repository.BookRepo;
import com.library.benjaMainLib.repository.BorrowRepo;
import com.library.benjaMainLib.service.BorrowService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.logging.Logger;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BorrowServiceTest {

    @Mock
    private BorrowRepo borrowRepo;

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BorrowService underTest;

    private final LocalDateTime localDateTime = LocalDateTime.now();

    @Test
    public void testGetAllBorrows(){
        //given
        Borrow borrow1 = new Borrow(1, "Beni", "Atomic Habits", localDateTime);
        Borrow borrow2 = new Borrow(2, "Ildi", "Csirkerizs", localDateTime);
        List<Borrow> borrowList = List.of(borrow1, borrow2);
        when(borrowRepo.findAll()).thenReturn(List.of(borrow1, borrow2));
        //when
        List<Borrow> testList = underTest.getAllBorrows();
        //then
        assertEquals(2,testList.size());
        Borrow testBorrow = testList.get(0);
        assertEquals(borrow1.getId(), testBorrow.getId());
        assertEquals(borrow1.getName(), testBorrow.getName());
        assertEquals(borrow1.getTitle(), testBorrow.getTitle());
        assertEquals(borrow1.getBorrowdate(), testBorrow.getBorrowdate());
        verify(borrowRepo, times(1)).findAll();
    }

    @Test
    public void testBookingCreatesBorrowIntoRepo(){
        //given
        Borrow borrow = new Borrow(1, "Beni", "Atomic Habits", localDateTime);
        Book book = new Book(1, "Atomic Habits", "Janos Elek", new Date(), 22, false);
        when(bookRepo.findAll()).thenReturn(List.of(book));
        //when
        underTest.booking(borrow);
        //then
        verify(borrowRepo).save(borrow);
    }

    @Test
    public void testWithDrawBorrowDeletesBorrowFromBorrowRepo(){
        //given
        Borrow borrow = new Borrow(1, "Beni", "Atomic Habits", localDateTime);
        Book book = new Book(1, "Atomic Habits", "Janos Elek", new Date(), 22, false);
        when(borrowRepo.findById(1)).thenReturn(Optional.of(borrow));
        when(bookRepo.findAll()).thenReturn(List.of(book));
        //when
        ResponseEntity<Borrow> borrowResponseEntityTest_1 = underTest.withDrawBorrow(1);
        ResponseEntity<Borrow> borrowResponseEntityTest_2 = underTest.withDrawBorrow(2);
        //then
        assertEquals(HttpStatus.OK, borrowResponseEntityTest_1.getStatusCode());
        assertEquals(HttpStatus.NOT_FOUND, borrowResponseEntityTest_2.getStatusCode());
        verify(borrowRepo).deleteById(1);
    }

    @Test
    public void testGetBookByName(){
        //given
        Book book = new Book(1, "Atomic Habits", "Janos Elek", new Date(), 22, false);
        when(bookRepo.findAll()).thenReturn(List.of(book));
        //when
        Optional<Book> testBook = underTest.getBookByName(book.getTitle());
        //then
        assertEquals(book, testBook.get());
        assertEquals(book.getId(), testBook.get().getId());
        assertEquals(book.getTitle(), testBook.get().getTitle());
        assertEquals(book.getAuthor(), testBook.get().getAuthor());
        assertEquals(book.getPublishdate(), testBook.get().getPublishdate());
        assertEquals(book.getPages(), testBook.get().getPages());
        assertEquals(book.isIsborrowed(), testBook.get().isIsborrowed());
        verify(bookRepo, times(1)).findAll();
    }
}
