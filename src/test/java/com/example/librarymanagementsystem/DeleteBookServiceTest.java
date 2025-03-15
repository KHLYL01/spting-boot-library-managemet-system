package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.exceptions.model.BookException;
import com.example.librarymanagementsystem.feature.book.repository.BookRepository;
import com.example.librarymanagementsystem.feature.book.service.DeleteBookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeleteBookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private DeleteBookService deleteBookService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void given_book_not_found_when_delete_book_service_throw_book_exception() {

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(BookException.class,() -> deleteBookService.execute(1L));

        verify(bookRepository, times(1)).findById(1L);

    }
}
