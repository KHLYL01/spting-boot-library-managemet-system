package com.example.librarymanagementsystem.feature.patron.service;

import com.example.librarymanagementsystem.helpers.Command;
import com.example.librarymanagementsystem.feature.patron.model.dto.PatronRequestDto;
import com.example.librarymanagementsystem.feature.patron.model.dto.PatronResponseDto;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import com.example.librarymanagementsystem.feature.patron.model.mapper.PatronMapper;
import com.example.librarymanagementsystem.feature.patron.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreatePatronService implements Command<PatronRequestDto, PatronResponseDto> {

    private final PatronRepository repo;
    private final PatronMapper mapper;

    @Override
    @Transactional
    @CacheEvict(value = "patronsCache", allEntries = true)
    public ResponseEntity<PatronResponseDto> execute(PatronRequestDto patronRequestDto) {
        Patron patron = mapper.toEntity(patronRequestDto);
        Patron savedPatron = repo.save(patron);
        PatronResponseDto patronResponseDto = mapper.toDto(savedPatron);
        return new ResponseEntity<>(patronResponseDto, HttpStatus.CREATED);
    }
}
