package com.example.librarymanagementsystem.feature.patron.service;

import com.example.librarymanagementsystem.helpers.Query;
import com.example.librarymanagementsystem.exceptions.model.PatronException;
import com.example.librarymanagementsystem.exceptions.model.enums.ErrorMessage;
import com.example.librarymanagementsystem.feature.patron.model.mapper.PatronMapper;
import com.example.librarymanagementsystem.feature.patron.model.dto.PatronResponseDto;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import com.example.librarymanagementsystem.feature.patron.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FindPatronByIdService implements Query<Long, PatronResponseDto> {

    private final PatronRepository repo;
    private final PatronMapper mapper;

    @Override
    @Cacheable(value = "patronsCache",key = "#id")
    public ResponseEntity<PatronResponseDto> execute(Long id) {
        Optional<Patron> book = repo.findById(id);
        if (book.isPresent()) {
            PatronResponseDto patronResponseDto = mapper.toDto(book.get());
            return new ResponseEntity<>(patronResponseDto, HttpStatus.OK);
        }
        throw new PatronException(ErrorMessage.P0001.getMessage(),ErrorMessage.P0001.getCode());
    }
}
