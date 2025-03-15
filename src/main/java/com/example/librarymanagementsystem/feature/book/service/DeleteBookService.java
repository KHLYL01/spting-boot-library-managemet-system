package com.example.librarymanagementsystem.feature.book.service;

import com.example.librarymanagementsystem.helpers.Command;
import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import com.example.librarymanagementsystem.feature.book.repository.BookRepository;
import com.example.librarymanagementsystem.exceptions.model.BookException;
import com.example.librarymanagementsystem.exceptions.model.enums.ErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeleteBookService implements Command<Long, Void> {

    private final BookRepository repo;

    @Override
    @CacheEvict(value = "booksCache",allEntries = true)
    public ResponseEntity<Void> execute(Long id) {
        Optional<Book> book = repo.findById(id);
        if(book.isPresent()){
            repo.delete(book.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        throw new BookException(ErrorMessage.B0001.getMessage(),ErrorMessage.B0001.getCode());
    }
}
