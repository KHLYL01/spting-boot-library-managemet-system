package com.example.librarymanagementsystem.feature.borrowRecord.service;

import com.example.librarymanagementsystem.exceptions.model.BorrowRecordException;
import com.example.librarymanagementsystem.exceptions.model.enums.ErrorMessage;
import com.example.librarymanagementsystem.feature.borrowRecord.model.dto.BorrowRecordRequest;
import com.example.librarymanagementsystem.feature.borrowRecord.model.dto.BorrowRecordResponse;
import com.example.librarymanagementsystem.feature.borrowRecord.model.entity.BorrowRecord;
import com.example.librarymanagementsystem.feature.borrowRecord.model.mapper.BorrowingRecordMapper;
import com.example.librarymanagementsystem.feature.borrowRecord.repository.BorrowRecordRepository;
import com.example.librarymanagementsystem.helpers.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReturnBookService implements Command<BorrowRecordRequest, BorrowRecordResponse> {

    private final BorrowRecordRepository repo;
    private final BorrowingRecordMapper mapper;

    @Override
    @Transactional
    public ResponseEntity<BorrowRecordResponse> execute(BorrowRecordRequest request) {

        if(repo.checkBookIsFoundOnLibrary(request.getBookId())){
            throw new BorrowRecordException(ErrorMessage.BR0003.getMessage(),ErrorMessage.BR0003.getCode());
        }

        Optional<BorrowRecord> borrowRecord = repo.findBorrowRecord(request.getBookId(),
                request.getPatronId());

        if (borrowRecord.isPresent()) {
            borrowRecord.get().setReturnDate(LocalDateTime.now());

            BorrowRecord savedBorrowRecord = repo.save(borrowRecord.get());
            BorrowRecordResponse borrowRecordResponse = mapper.toDto(savedBorrowRecord);

            return new ResponseEntity<>(borrowRecordResponse, HttpStatus.OK);
        }
        throw new BorrowRecordException(ErrorMessage.BR0001.getMessage(),ErrorMessage.BR0001.getCode());
    }
}
