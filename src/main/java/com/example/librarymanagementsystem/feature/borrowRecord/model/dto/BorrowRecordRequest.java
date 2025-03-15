package com.example.librarymanagementsystem.feature.borrowRecord.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BorrowRecordRequest {

    private Long bookId;
    private Long patronId;

}
