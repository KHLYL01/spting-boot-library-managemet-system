package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.feature.book.model.dto.BookResponseDto;
import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import com.example.librarymanagementsystem.feature.book.model.mapper.BookMapper;
import com.example.librarymanagementsystem.feature.book.repository.BookRepository;
import com.example.librarymanagementsystem.feature.book.service.FindAllBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FindAllBookServiceTest {
    @Mock
    private BookRepository bookRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private FindAllBookService findAllBookService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_books_exists_when_find_all_book_service_return_books_dto(){

        List<Book> books = List.of(
                Book.builder()
                        .id(1L)
                        .title("book1")
                        .author("author1")
                        .isbn("1234567889")
                        .build(),
                Book.builder()
                        .id(2L)
                        .title("book2")
                        .author("author2")
                        .isbn("1234567889")
                        .build()
        );

        when(bookRepository.findAll()).thenReturn(books);

        ResponseEntity<List<BookResponseDto>> response = findAllBookService.execute(null);

        assertEquals(ResponseEntity.ok(bookMapper.toDtos(books)),response);

        verify(bookRepository,times(1)).findAll();

    }
}
