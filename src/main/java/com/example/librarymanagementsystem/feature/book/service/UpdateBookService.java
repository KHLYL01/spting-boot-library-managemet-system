package com.example.librarymanagementsystem.feature.book.service;

import com.example.librarymanagementsystem.helpers.Command;
import com.example.librarymanagementsystem.feature.book.model.dto.BookResponseDto;
import com.example.librarymanagementsystem.feature.book.model.dto.UpdateBookDto;
import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import com.example.librarymanagementsystem.feature.book.model.mapper.BookMapper;
import com.example.librarymanagementsystem.feature.book.repository.BookRepository;
import com.example.librarymanagementsystem.exceptions.model.BookException;
import com.example.librarymanagementsystem.exceptions.model.enums.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdateBookService implements Command<UpdateBookDto, BookResponseDto> {

    private final BookRepository repo;
    private final BookMapper mapper;

    @Override
    @Transactional
    @CacheEvict(value = "booksCache", allEntries = true)
    public ResponseEntity<BookResponseDto> execute(UpdateBookDto updateBookDto) {
        Optional<Book> book = repo.findById(updateBookDto.getId());

        if(book.isPresent()) {
            mapper.toEntity(book.get(),updateBookDto.getBookRequestDto());
            Book savedBook = repo.save(book.get());
            BookResponseDto bookResponseDto = mapper.toDto(savedBook);
            return new ResponseEntity<>(bookResponseDto, HttpStatus.OK);
        }
        throw new BookException(ErrorMessage.B0001.getMessage(),ErrorMessage.B0001.getCode());
    }
}
