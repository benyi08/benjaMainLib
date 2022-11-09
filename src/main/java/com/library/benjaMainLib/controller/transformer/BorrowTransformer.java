package com.library.benjaMainLib.controller.transformer;

import com.library.benjaMainLib.controller.model.BorrowDetailView;
import com.library.benjaMainLib.controller.model.BorrowSummaryView;
import com.library.benjaMainLib.model.Borrow;
import org.springframework.stereotype.Component;

@Component
public class BorrowTransformer {

    public BorrowSummaryView transformBorrowSummaryView(Borrow borrow){
        BorrowSummaryView borrowSummaryView = new BorrowSummaryView();
        borrowSummaryView.setId(borrow.getId());
        borrowSummaryView.setName(borrow.getName());
        borrowSummaryView.setTitle(borrow.getTitle());
        return borrowSummaryView;
    }

    public BorrowDetailView transformBorrowDetailView(Borrow borrow){
        BorrowDetailView borrowDetailView = new BorrowDetailView();
        borrowDetailView.setId(borrow.getId());
        borrowDetailView.setName(borrow.getName());
        borrowDetailView.setTitle(borrow.getTitle());
        borrowDetailView.setBorrowdate(borrow.getBorrowdate());
        return borrowDetailView;
    }
}
