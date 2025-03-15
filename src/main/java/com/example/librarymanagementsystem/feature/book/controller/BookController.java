package com.example.librarymanagementsystem.feature.book.controller;

import com.example.librarymanagementsystem.feature.book.model.dto.BookRequestDto;
import com.example.librarymanagementsystem.feature.book.model.dto.UpdateBookDto;
import com.example.librarymanagementsystem.feature.book.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final FindAllBookService findAllBookService;
    private final FindBookByIdService findBookByIdService;
    private final CreateBookService createBookService;
    private final UpdateBookService updateBookService;
    private final DeleteBookService deleteBookService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return findAllBookService.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return findBookByIdService.execute(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody BookRequestDto request) {
        return createBookService.execute(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody BookRequestDto request) {
        return updateBookService.execute(UpdateBookDto.builder()
                .id(id)
                .bookRequestDto(request)
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return deleteBookService.execute(id);
    }
}
