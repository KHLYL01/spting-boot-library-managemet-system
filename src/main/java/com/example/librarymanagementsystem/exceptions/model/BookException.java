package com.example.librarymanagementsystem.exceptions.model;

import lombok.Getter;

@Getter
public class BookException extends RuntimeException{

    private final String code;

    public BookException(String message, String code) {
        super(message);
        this.code = code;
    }
}
