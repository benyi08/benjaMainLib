package com.library.benjaMainLib.service;

import com.library.benjaMainLib.model.Borrow;
import com.library.benjaMainLib.repository.BorrowRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BorrowService {

    @Autowired
    BorrowRepo borrowRepo;

    public Iterable<Borrow> getBorrows() {
        return borrowRepo.findAll();
    }

    public ResponseEntity<Borrow> createBorrow(Borrow borrow) {
        return ResponseEntity.ok(borrowRepo.save(borrow));
    }
}
