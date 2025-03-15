package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.exceptions.model.PatronException;
import com.example.librarymanagementsystem.feature.patron.model.dto.PatronResponseDto;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import com.example.librarymanagementsystem.feature.patron.model.mapper.PatronMapper;
import com.example.librarymanagementsystem.feature.patron.repository.PatronRepository;
import com.example.librarymanagementsystem.feature.patron.service.FindPatronByIdService;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import com.example.librarymanagementsystem.feature.patron.model.mapper.PatronMapper;
import com.example.librarymanagementsystem.feature.patron.repository.PatronRepository;
import com.example.librarymanagementsystem.feature.patron.service.FindPatronByIdService;
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

public class FindPatronByIdServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @Mock
    private PatronMapper patronMapper;

    @InjectMocks
    private FindPatronByIdService findPatronByIdService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_patron_exists_when_find_patron_by_id_service_return_patrons_dto() {

        Patron patron = Patron.builder()
                .id(1L)
                .name("patron1")
                .contactInformation("0912345678")
                .build();

        when(patronRepository.findById(1L)).thenReturn(Optional.of(patron));

        ResponseEntity<PatronResponseDto> response = findPatronByIdService.execute(1L);

        assertEquals(ResponseEntity.ok(patronMapper.toDto(patron)), response);

        verify(patronRepository, times(1)).findById(1L);

    }

    @Test
    public void given_patron_not_found_when_find_patron_by_id_service_throw_patron_exception() {

        when(patronRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PatronException.class,() -> findPatronByIdService.execute(1L));

        verify(patronRepository, times(1)).findById(1L);

    }
}
