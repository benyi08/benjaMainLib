package com.library.benjaMainLib.controller.model;

import lombok.Data;

import java.util.Date;

@Data
public class BookDetailView {
    private int id;
    private String title;
    private String author;
    private Date publishdate;
    private int pages;
    private boolean isborrowed;
}
