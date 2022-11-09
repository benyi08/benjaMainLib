package com.library.benjaMainLib.controller.transformer;

import com.library.benjaMainLib.controller.model.BookDetailView;
import com.library.benjaMainLib.controller.model.BookSummaryView;
import com.library.benjaMainLib.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookTransformer {
    public BookDetailView transformBookDetailView(Book book){
        BookDetailView bookWithDetail = new BookDetailView();
        bookWithDetail.setId(book.getId());
        bookWithDetail.setTitle(book.getTitle());
        bookWithDetail.setAuthor(book.getAuthor());
        bookWithDetail.setPublishdate(book.getPublishdate());
        bookWithDetail.setPages(book.getPages());
        bookWithDetail.setIsborrowed(book.isIsborrowed());

        return bookWithDetail;
    }

    public BookSummaryView transformBookSummaryView(Book book){
        BookSummaryView bookSummaryView = new BookSummaryView();
        bookSummaryView.setId(book.getId());
        bookSummaryView.setTitle(book.getTitle());
        bookSummaryView.setIsborrowed(book.isIsborrowed());

        return bookSummaryView;
    }
}
