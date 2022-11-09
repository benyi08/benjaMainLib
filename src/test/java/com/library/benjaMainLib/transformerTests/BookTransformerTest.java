package com.library.benjaMainLib.transformerTests;

import com.library.benjaMainLib.controller.model.BookDetailView;
import com.library.benjaMainLib.controller.model.BookSummaryView;
import com.library.benjaMainLib.controller.transformer.BookTransformer;
import com.library.benjaMainLib.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BookTransformerTest {

    private final BookTransformer underTest = new BookTransformer();

    @Test
    public void testBookSummaryViewShouldWork(){
        //given
        Book book = new Book(1, "Ősz", "Lovasz", new Date(), 10, false);
        //when
        BookSummaryView bookSummaryView = underTest.transformBookSummaryView(book);
        //then
        assertEquals(book.getId(), bookSummaryView.getId());
        assertEquals(book.getTitle(), bookSummaryView.getTitle());
        assertEquals(book.isIsborrowed(), bookSummaryView.isIsborrowed());
    }

    @Test
    public void testBookDetailViewShouldWork(){
        //given
        Book book = new Book(1, "Tél", "Lovasz", new Date(), 10, false);
        //when
        BookDetailView bookDetailView = underTest.transformBookDetailView(book);
        //then
        assertEquals(book.getId(), bookDetailView.getId());
        assertEquals(book.getTitle(), bookDetailView.getTitle());
        assertEquals(book.getAuthor(), bookDetailView.getAuthor());
        assertEquals(book.getPublishdate(), bookDetailView.getPublishdate());
        assertEquals(book.getPages(), bookDetailView.getPages());
        assertEquals(book.isIsborrowed(), bookDetailView.isIsborrowed());
    }

}
