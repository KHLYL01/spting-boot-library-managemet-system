package com.example.librarymanagementsystem.exceptions.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ErrorResponse {

    private Date timestamp;
    private int statusCode;
    private String code;
    private String message;

}
