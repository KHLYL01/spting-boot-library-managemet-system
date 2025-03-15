package com.example.librarymanagementsystem.feature.book.model.dto;

import lombok.Data;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private String author;
    private int publicationYear;
    private String isbn;
}
