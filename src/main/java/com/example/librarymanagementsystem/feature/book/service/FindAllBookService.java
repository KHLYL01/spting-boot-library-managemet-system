package com.example.librarymanagementsystem.feature.book.service;

import com.example.librarymanagementsystem.helpers.Query;
import com.example.librarymanagementsystem.feature.book.model.dto.BookResponseDto;
import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import com.example.librarymanagementsystem.feature.book.model.mapper.BookMapper;
import com.example.librarymanagementsystem.feature.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllBookService implements Query<Void, List<BookResponseDto>> {

    private final BookRepository repo;
    private final BookMapper mapper;

    @Override
    @Cacheable(value = "booksCache")
    public ResponseEntity<List<BookResponseDto>> execute(Void input) {
        List<Book> books = repo.findAll();
        List<BookResponseDto> bookResponseDtos = mapper.toDtos(books);
        return new ResponseEntity<>(bookResponseDtos, HttpStatus.OK);
    }
}
