package com.example.librarymanagementsystem.feature.book.service;

import com.example.librarymanagementsystem.helpers.Query;
import com.example.librarymanagementsystem.feature.book.model.dto.BookResponseDto;
import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import com.example.librarymanagementsystem.feature.book.model.mapper.BookMapper;
import com.example.librarymanagementsystem.feature.book.repository.BookRepository;
import com.example.librarymanagementsystem.exceptions.model.BookException;
import com.example.librarymanagementsystem.exceptions.model.enums.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindBookByIdService implements Query<Long, BookResponseDto> {

    private final BookRepository repo;
    private final BookMapper mapper;

    @Override
    @Cacheable(value = "booksCache",key = "#id")
    public ResponseEntity<BookResponseDto> execute(Long id) {
        Optional<Book> book = repo.findById(id);
        if (book.isPresent()) {
            BookResponseDto bookResponseDto = mapper.toDto(book.get());
            return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
        }
        throw new BookException(ErrorMessage.B0001.getMessage(),ErrorMessage.B0001.getCode());
    }
}
