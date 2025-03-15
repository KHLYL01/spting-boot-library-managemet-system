package com.example.librarymanagementsystem.feature.patron.service;

import com.example.librarymanagementsystem.helpers.Query;
import com.example.librarymanagementsystem.feature.patron.model.dto.PatronResponseDto;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import com.example.librarymanagementsystem.feature.patron.model.mapper.PatronMapper;
import com.example.librarymanagementsystem.feature.patron.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAllPatronService implements Query<Void, List<PatronResponseDto>> {

    private final PatronRepository repo;
    private final PatronMapper mapper;

    @Override
    @Cacheable(value = "patronsCache")
    public ResponseEntity<List<PatronResponseDto>> execute(Void input) {
        List<Patron> patrons = repo.findAll();
        List<PatronResponseDto> patronResponseDtos = mapper.toDtos(patrons);
        return new ResponseEntity<>(patronResponseDtos, HttpStatus.OK);
    }
}
