package com.example.librarymanagementsystem.exceptions.model;

import lombok.Getter;

@Getter
public class BorrowRecordException extends RuntimeException{

    private final String code;

    public BorrowRecordException(String message, String code) {
        super(message);
        this.code = code;
    }
}
