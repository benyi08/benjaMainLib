package com.library.benjaMainLib.serviceTests;

import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.repository.BookRepo;
import com.library.benjaMainLib.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class BookServiceTest {
    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookService underTest;

    @Test
    public void testGetAllBooksShouldReturnAllBooksFromDatabase() {
        // given
        Book book = new Book(1, "Benike", "Lovasz", new Date(), 10, false);
        List<Book> books = List.of(book);
        Mockito.when(bookRepo.findAll()).thenReturn(books);
        // when
        List<Book> result = underTest.getAllBooks();
        // then
        assertEquals(1, result.size());
        Book resultBook = result.get(0);
        assertEquals(book.getId(), resultBook.getId());
        assertEquals(book.getPublishdate(), resultBook.getPublishdate());
        assertEquals(book.getPages(), resultBook.getPages());
        assertEquals(book.getAuthor(), resultBook.getAuthor());
        assertEquals(book.getTitle(), resultBook.getTitle());
        assertEquals(book.isIsborrowed(), resultBook.isIsborrowed());
        Mockito.verify(bookRepo, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetAllAvailableBooksShouldReturnOnlyNonBorrowedBooks() {
        // given
        Book book1 = new Book(1, "Benike", "Lovasz", new Date(), 10, false);
        Book book2 = new Book(2, "Benike2", "Lovasz2", new Date(), 12, true);
        List<Book> books = List.of(book1, book2);
        Mockito.when(bookRepo.findAll()).thenReturn(books);
        // when
        List<Book> result = underTest.getAllAvailableBooks();
        // then
        assertEquals(1, result.size());
        Book resultBook = result.get(0);
        assertEquals(book1.getId(), resultBook.getId());
        assertEquals(book1.getPublishdate(), resultBook.getPublishdate());
        assertEquals(book1.getPages(), resultBook.getPages());
        assertEquals(book1.getAuthor(), resultBook.getAuthor());
        assertEquals(book1.getTitle(), resultBook.getTitle());
        assertEquals(book1.isIsborrowed(), resultBook.isIsborrowed());
        Mockito.verify(bookRepo, Mockito.times(1)).findAll();
    }

    @Test
    public void testGetAvailableBookShouldReturnNonBorrowedExistingBookById() {
        //given
        Book book1 = new Book(1, "Benike", "Lovasz", new Date(), 10, false);
        Book book2 = new Book(2, "Beni", "Lovas", new Date(), 12, true);
        Mockito.when(bookRepo.findById(1)).thenReturn(Optional.of(book1));
        Mockito.when(bookRepo.findById(2)).thenReturn(Optional.of(book2));
        //when
        Optional<Book> testBook = underTest.getAvailableBook(1);
        Optional<Book> testBook2 = underTest.getAvailableBook(2);
        //then
        assertEquals(book1, testBook.get());
        assertEquals(Optional.empty(), testBook2);
        Book resultBook = testBook.get();
        assertEquals(resultBook.getId(), book1.getId());
        assertEquals(resultBook.getTitle(), book1.getTitle());
        assertEquals(resultBook.getAuthor(), book1.getAuthor());
        assertEquals(resultBook.getPublishdate(), book1.getPublishdate());
        assertEquals(resultBook.getPages(), book1.getPages());
        Mockito.verify(bookRepo, Mockito.times(1)).findById(1);
    }

    @Test
    public void testGetOneBook(){
        //given
        Book book1 = new Book(1, "Benike", "Lovasz", new Date(), 10, false);
        Mockito.when(bookRepo.findById(1)).thenReturn(Optional.of(book1));
        Mockito.when(bookRepo.findById(2)).thenReturn(Optional.empty());
        //when
        Optional<Book> testBook1 = underTest.getOneBook(1);
        Optional<Book> testBook2 = underTest.getOneBook(2);
        //then
        assertEquals(book1, testBook1.get());
        assertEquals(Optional.empty(), testBook2);

        Book resultBook = testBook1.get();
        assertEquals(resultBook.getId(), book1.getId());
        assertEquals(resultBook.getTitle(), book1.getTitle());
        assertEquals(resultBook.getAuthor(), book1.getAuthor());
        assertEquals(resultBook.getPublishdate(), book1.getPublishdate());
        assertEquals(resultBook.getPages(), book1.getPages());
        Mockito.verify(bookRepo, Mockito.times(1)).findById(1);
    }

    @Test//bad
    public void testCreateBookCreatesBookInRepository(){
        //given
        Book book1 = new Book(1, "Benike", "Lovasz", new Date(), 10, false);
        //when
        underTest.createBook(book1);
        //then
        Mockito.verify(bookRepo).save(book1);
    }

    @Test
    public void testDeleteBookDeletesBookFromRepository(){
        //given
        Book book1 = new Book(1, "Benike", "Lovasz", new Date(), 10, false);
        Mockito.when(bookRepo.findById(1)).thenReturn(Optional.of(book1));
        //when
        underTest.deleteBook(1);
        //then
        Mockito.verify(bookRepo).deleteById(1);
    }

    @Test
    public void testUpdateBookChangesPropertiesOfBookChosenById(){
        //given
        Book book1 = new Book(1, "Benike", "Lovasz", new Date(), 10, false);
        Book book2 = new Book(1, "Beni", "Lovas", new Date(), 12, false);
        Mockito.when(bookRepo.findById(1)).thenReturn(Optional.of(book1));
        Mockito.when(bookRepo.findById(2)).thenReturn(Optional.empty());
        //when
        ResponseEntity<Book> testBook1 = underTest.updateBook(1, book2);
        ResponseEntity<Book> testBook2 = underTest.updateBook(2, book2);
        //then
        assertEquals(book2, testBook1.getBody());
        assertEquals(HttpStatus.NOT_FOUND, testBook2.getStatusCode());
        Mockito.verify(bookRepo).save(book2);
    }
}




















