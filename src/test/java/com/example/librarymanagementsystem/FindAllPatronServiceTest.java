package com.example.librarymanagementsystem;

import com.example.librarymanagementsystem.feature.patron.model.dto.PatronResponseDto;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import com.example.librarymanagementsystem.feature.patron.model.mapper.PatronMapper;
import com.example.librarymanagementsystem.feature.patron.repository.PatronRepository;
import com.example.librarymanagementsystem.feature.patron.service.FindAllPatronService;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import com.example.librarymanagementsystem.feature.patron.model.mapper.PatronMapper;
import com.example.librarymanagementsystem.feature.patron.service.FindAllPatronService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class FindAllPatronServiceTest {
    @Mock
    private PatronRepository patronRepository;

    @Mock
    private PatronMapper patronMapper;

    @InjectMocks
    private FindAllPatronService findAllPatronService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_patrons_exists_when_find_all_patron_service_return_patrons_dto(){

        List<Patron> patrons = List.of(
                Patron.builder()
                        .id(1L)
                        .name("patron1")
                        .contactInformation("0912345678")
                        .build(),
                Patron.builder()
                        .id(2L)
                        .name("patron2")
                        .contactInformation("0987654321")
                        .build()
                );

        when(patronRepository.findAll()).thenReturn(patrons);

        ResponseEntity<List<PatronResponseDto>> response = findAllPatronService.execute(null);

        assertEquals(ResponseEntity.ok(patronMapper.toDtos(patrons)),response);

        verify(patronRepository,times(1)).findAll();

    }
}
