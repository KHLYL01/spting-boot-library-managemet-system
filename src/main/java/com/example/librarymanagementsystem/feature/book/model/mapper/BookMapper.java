package com.example.librarymanagementsystem.feature.book.model.mapper;


import com.example.librarymanagementsystem.feature.book.model.dto.BookRequestDto;
import com.example.librarymanagementsystem.feature.book.model.dto.BookResponseDto;
import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface BookMapper {

    BookResponseDto toDto(Book entity);

    List<BookResponseDto> toDtos(List<Book> entities);

    @Mapping(target = "id",ignore = true)
    Book toEntity(BookRequestDto dto);

    @Mapping(target = "id",ignore = true)
    void toEntity(@MappingTarget Book entity , BookRequestDto dto);

}
