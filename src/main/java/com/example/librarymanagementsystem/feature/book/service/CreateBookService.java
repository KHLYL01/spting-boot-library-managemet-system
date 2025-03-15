package com.example.librarymanagementsystem.feature.book.service;

import com.example.librarymanagementsystem.helpers.Command;
import com.example.librarymanagementsystem.feature.book.model.dto.BookRequestDto;
import com.example.librarymanagementsystem.feature.book.model.dto.BookResponseDto;
import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import com.example.librarymanagementsystem.feature.book.model.mapper.BookMapper;
import com.example.librarymanagementsystem.feature.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateBookService implements Command<BookRequestDto, BookResponseDto> {

    private final BookRepository repo;
    private final BookMapper mapper;

    @Override
    @Transactional
    @CacheEvict(value = "booksCache", allEntries = true)
    public ResponseEntity<BookResponseDto> execute(BookRequestDto bookRequestDto) {
        Book book = mapper.toEntity(bookRequestDto);
        Book savedBook = repo.save(book);
        BookResponseDto bookResponseDto = mapper.toDto(savedBook);
        return new ResponseEntity<>(bookResponseDto, HttpStatus.CREATED);
    }
}
