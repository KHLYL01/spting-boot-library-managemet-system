package com.example.librarymanagementsystem.exceptions.model.enums;

public enum ErrorMessage {
    B0001("Book Not Found","B-0001"),

    P0001("Patron Not Found","P-0001"),

    BR0001("Borrow Record Not Found","BR-0001"),
    BR0002("Book Is Already Found In Library","BR-0002"),
    BR0003("Book Is Not Found In Library","BR-0003");

    private final String message;
    private final String code;

    ErrorMessage(String message,String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage(){
        return this.message;
    }

    public String getCode(){
        return this.code;
    }

}
