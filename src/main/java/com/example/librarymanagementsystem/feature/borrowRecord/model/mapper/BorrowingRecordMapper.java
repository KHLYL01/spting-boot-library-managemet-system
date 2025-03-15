package com.example.librarymanagementsystem.feature.borrowRecord.model.mapper;

import com.example.librarymanagementsystem.feature.book.model.mapper.BookMapper;
import com.example.librarymanagementsystem.feature.borrowRecord.model.dto.BorrowRecordResponse;
import com.example.librarymanagementsystem.feature.borrowRecord.model.entity.BorrowRecord;
import com.example.librarymanagementsystem.feature.patron.model.mapper.PatronMapper;
import org.mapstruct.Mapper;

@Mapper(uses = {BookMapper.class, PatronMapper.class})
public interface BorrowingRecordMapper {

    BorrowRecordResponse toDto(BorrowRecord entity);

}
