package com.library.benjaMainLib.controller;

import com.library.benjaMainLib.model.Book;
import com.library.benjaMainLib.model.Borrow;
import com.library.benjaMainLib.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    BorrowService borrowService;

    @GetMapping("")
    public Iterable<Borrow> getBorrows(){
        return borrowService.getBorrows();
    }

    @PostMapping("/create")
    public ResponseEntity<Borrow> createBorrow(@RequestBody Borrow borrow){
        return borrowService.createBorrow(borrow);
    }

}
