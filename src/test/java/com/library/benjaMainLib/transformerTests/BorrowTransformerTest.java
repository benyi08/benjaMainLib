package com.library.benjaMainLib.transformerTests;

import com.library.benjaMainLib.controller.model.BookDetailView;
import com.library.benjaMainLib.controller.model.BorrowDetailView;
import com.library.benjaMainLib.controller.model.BorrowSummaryView;
import com.library.benjaMainLib.controller.transformer.BorrowTransformer;
import com.library.benjaMainLib.model.Borrow;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class BorrowTransformerTest {

    private final BorrowTransformer underTest = new BorrowTransformer();

    private final LocalDateTime localDateTime = LocalDateTime.now();

    @Test
    public void testBorrowSummaryViewShouldWork(){
        //given
        Borrow borrow = new Borrow(1, "Beni", "Atomic Habits", localDateTime);
        //when
        BorrowSummaryView borrowSummaryView = underTest.transformBorrowSummaryView(borrow);
        //then
        assertEquals(borrow.getId(), borrowSummaryView.getId());
        assertEquals(borrow.getName(), borrowSummaryView.getName());
        assertEquals(borrow.getTitle(), borrowSummaryView.getTitle());
    }

    @Test
    public void testBorrowDetailViewShouldWork(){
        //given
        Borrow borrow = new Borrow(1, "Beni", "Atomic Habits", localDateTime);
        //when
        BorrowDetailView borrowDetailView = underTest.transformBorrowDetailView(borrow);
        // then
        assertEquals(borrow.getId(), borrowDetailView.getId());
        assertEquals(borrow.getName(), borrowDetailView.getName());
        assertEquals(borrow.getTitle(), borrowDetailView.getTitle());
        assertEquals(borrow.getTitle(), borrowDetailView.getTitle());
    }
}
