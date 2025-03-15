package com.example.librarymanagementsystem.feature.borrowRecord.controller;


import com.example.librarymanagementsystem.feature.borrowRecord.model.dto.BorrowRecordRequest;
import com.example.librarymanagementsystem.feature.borrowRecord.service.BorrowBookService;
import com.example.librarymanagementsystem.feature.borrowRecord.service.ReturnBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BorrowRecordController {

    private final BorrowBookService borrowBookService;
    private final ReturnBookService returnBookService;


    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<?> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return borrowBookService.execute(BorrowRecordRequest.builder()
                .bookId(bookId)
                .patronId(patronId)
                .build()
        );
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<?> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        return returnBookService.execute(BorrowRecordRequest.builder()
                .bookId(bookId)
                .patronId(patronId)
                .build()
        );
    }

}
