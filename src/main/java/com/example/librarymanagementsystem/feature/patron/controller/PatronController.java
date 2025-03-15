package com.example.librarymanagementsystem.feature.patron.controller;

import com.example.librarymanagementsystem.feature.patron.model.dto.PatronRequestDto;
import com.example.librarymanagementsystem.feature.patron.model.dto.UpdatePatronDto;
import com.example.librarymanagementsystem.feature.patron.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patrons")
@RequiredArgsConstructor
public class PatronController {

    private final FindAllPatronService findAllPatronService;
    private final FindPatronByIdService findPatronByIdService;
    private final CreatePatronService createPatronService;
    private final UpdatePatronService updatePatronService;
    private final DeletePatronService deletePatronService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return findAllPatronService.execute(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return findPatronByIdService.execute(id);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PatronRequestDto request) {
        return createPatronService.execute(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,@Valid @RequestBody PatronRequestDto request) {
        return updatePatronService.execute(UpdatePatronDto.builder()
                .id(id)
                .patronRequestDto(request)
                .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return deletePatronService.execute(id);
    }
}
