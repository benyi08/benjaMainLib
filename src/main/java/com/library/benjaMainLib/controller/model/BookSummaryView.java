package com.library.benjaMainLib.controller.model;

import lombok.Data;

@Data
public class BookSummaryView {
    private int id;
    private String title;
    private boolean isborrowed;
}
