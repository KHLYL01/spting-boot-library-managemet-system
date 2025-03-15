package com.example.librarymanagementsystem.feature.borrowRecord.model.dto;

import com.example.librarymanagementsystem.feature.book.model.dto.BookResponseDto;
import com.example.librarymanagementsystem.feature.patron.model.dto.PatronResponseDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BorrowRecordResponse {

    private int id;
    private BookResponseDto book;
    private PatronResponseDto patron;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;

}
