package com.example.librarymanagementsystem.feature.patron.service;

import com.example.librarymanagementsystem.helpers.Command;
import com.example.librarymanagementsystem.exceptions.model.PatronException;
import com.example.librarymanagementsystem.exceptions.model.enums.ErrorMessage;
import com.example.librarymanagementsystem.feature.patron.model.dto.PatronResponseDto;
import com.example.librarymanagementsystem.feature.patron.model.dto.UpdatePatronDto;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import com.example.librarymanagementsystem.feature.patron.model.mapper.PatronMapper;
import com.example.librarymanagementsystem.feature.patron.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UpdatePatronService implements Command<UpdatePatronDto, PatronResponseDto> {

    private final PatronRepository repo;
    private final PatronMapper mapper;

    @Override
    @Transactional
    @CacheEvict(value = "patronsCache", allEntries = true)
    public ResponseEntity<PatronResponseDto> execute(UpdatePatronDto updatePatronDto) {
        Optional<Patron> book = repo.findById(updatePatronDto.getId());

        if(book.isPresent()) {
            mapper.toEntity(book.get(), updatePatronDto.getPatronRequestDto());
            Patron savedPatron = repo.save(book.get());
            PatronResponseDto patronResponseDto = mapper.toDto(savedPatron);
            return new ResponseEntity<>(patronResponseDto, HttpStatus.OK);
        }
        throw new PatronException(ErrorMessage.P0001.getMessage(),ErrorMessage.P0001.getCode());
    }
}
