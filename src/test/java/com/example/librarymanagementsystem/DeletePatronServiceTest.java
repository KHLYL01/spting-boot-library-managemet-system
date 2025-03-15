package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.exceptions.model.PatronException;
import com.example.librarymanagementsystem.feature.patron.repository.PatronRepository;
import com.example.librarymanagementsystem.feature.patron.service.DeletePatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeletePatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private DeletePatronService deletePatronService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void given_patron_not_found_when_delete_patron_service_throw_patron_exception() {

        when(patronRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PatronException.class,() -> deletePatronService.execute(1L));

        verify(patronRepository, times(1)).findById(1L);

    }
}
