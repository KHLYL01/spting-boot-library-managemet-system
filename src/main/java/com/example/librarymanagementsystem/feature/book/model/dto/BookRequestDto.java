package com.example.librarymanagementsystem.feature.book.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookRequestDto {

    @NotNull(message = "Title Is Required")
    @NotEmpty(message = "Title must be not empty")
    private String title;

    @NotNull(message = "Author Is Required")
    @NotEmpty(message = "Author must be not empty")
    private String author;

    @NotNull(message = "Publication Year Is Required")
    @Positive(message = "Publication year must be positive")

    private int publicationYear;

    private String isbn;

}
