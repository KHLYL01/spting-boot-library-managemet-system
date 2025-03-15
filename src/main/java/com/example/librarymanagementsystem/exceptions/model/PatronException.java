package com.example.librarymanagementsystem.exceptions.model;

import lombok.Getter;

@Getter
public class PatronException extends RuntimeException{

    private final String code;

    public PatronException(String message, String code) {
        super(message);
        this.code = code;
    }
}
