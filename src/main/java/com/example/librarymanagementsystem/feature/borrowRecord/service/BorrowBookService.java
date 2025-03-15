package com.example.librarymanagementsystem.feature.borrowRecord.service;

import com.example.librarymanagementsystem.exceptions.model.BorrowRecordException;
import com.example.librarymanagementsystem.helpers.Command;
import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import com.example.librarymanagementsystem.feature.book.repository.BookRepository;
import com.example.librarymanagementsystem.feature.borrowRecord.model.dto.BorrowRecordRequest;
import com.example.librarymanagementsystem.feature.borrowRecord.model.dto.BorrowRecordResponse;
import com.example.librarymanagementsystem.feature.borrowRecord.model.entity.BorrowRecord;
import com.example.librarymanagementsystem.feature.borrowRecord.model.mapper.BorrowingRecordMapper;
import com.example.librarymanagementsystem.feature.borrowRecord.repository.BorrowRecordRepository;
import com.example.librarymanagementsystem.exceptions.model.BookException;
import com.example.librarymanagementsystem.exceptions.model.PatronException;
import com.example.librarymanagementsystem.exceptions.model.enums.ErrorMessage;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import com.example.librarymanagementsystem.feature.patron.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BorrowBookService implements Command<BorrowRecordRequest, BorrowRecordResponse> {

    private final BorrowRecordRepository repo;
    private final BookRepository bookRepo;
    private final PatronRepository patronRepo;
    private final BorrowingRecordMapper mapper;

    @Override
    @Transactional
    public ResponseEntity<BorrowRecordResponse> execute(BorrowRecordRequest request) {
        Optional<Book> book = bookRepo.findById(request.getBookId());
        if (book.isEmpty()) {
            throw new BookException(ErrorMessage.B0001.getMessage(),ErrorMessage.B0001.getCode());
        }
        Optional<Patron> patron = patronRepo.findById(request.getPatronId());
        if (patron.isEmpty()) {
            throw new PatronException(ErrorMessage.P0001.getMessage(),ErrorMessage.P0001.getCode());
        }

        if(!repo.checkBookIsFoundOnLibrary(request.getBookId())){
            throw new BorrowRecordException(ErrorMessage.BR0002.getMessage(),ErrorMessage.BR0002.getCode());
        }


        BorrowRecord borrowRecord = new BorrowRecord();
        borrowRecord.setBook(book.get());
        borrowRecord.setPatron(patron.get());
        borrowRecord.setBorrowDate(LocalDateTime.now());

        BorrowRecord savedBorrowRecord = repo.save(borrowRecord);
        BorrowRecordResponse borrowRecordResponse = mapper.toDto(savedBorrowRecord);

        return new ResponseEntity<>(borrowRecordResponse, HttpStatus.CREATED);
    }
}
