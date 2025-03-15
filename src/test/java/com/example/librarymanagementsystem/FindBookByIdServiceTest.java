package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.exceptions.model.BookException;
import com.example.librarymanagementsystem.feature.book.model.dto.BookResponseDto;
import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import com.example.librarymanagementsystem.feature.book.model.mapper.BookMapper;
import com.example.librarymanagementsystem.feature.book.repository.BookRepository;
import com.example.librarymanagementsystem.feature.book.service.FindBookByIdService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class FindBookByIdServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private FindBookByIdService findBookByIdService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_book_exists_when_find_book_by_id_service_return_books_dto() {

        Book book = Book.builder()
                .id(1L)
                .title("book1")
                .author("author1")
                .isbn("1234567889")
                .build();

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        ResponseEntity<BookResponseDto> response = findBookByIdService.execute(1L);

        assertEquals(ResponseEntity.ok(bookMapper.toDto(book)), response);

        verify(bookRepository, times(1)).findById(1L);

    }

    @Test
    public void given_book_not_found_when_find_book_by_id_service_throw_book_exception() {

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookException.class,() -> findBookByIdService.execute(1L));

        verify(bookRepository, times(1)).findById(1L);

    }
}
