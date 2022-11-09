package com.library.benjaMainLib.controller.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowDetailView {

    private int id;
    private String name;
    private String title;
    private LocalDateTime borrowdate;

}
