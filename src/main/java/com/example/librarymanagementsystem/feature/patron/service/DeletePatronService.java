package com.example.librarymanagementsystem.feature.patron.service;

import com.example.librarymanagementsystem.helpers.Command;
import com.example.librarymanagementsystem.exceptions.model.PatronException;
import com.example.librarymanagementsystem.exceptions.model.enums.ErrorMessage;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import com.example.librarymanagementsystem.feature.patron.repository.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DeletePatronService implements Command<Long, Void> {

    private final PatronRepository repo;

    @Override
    @CacheEvict(value = "patronsCache", allEntries = true)
    public ResponseEntity<Void> execute(Long id) {
        Optional<Patron> book = repo.findById(id);
        if(book.isPresent()){
            repo.delete(book.get());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        throw new PatronException(ErrorMessage.P0001.getMessage(),ErrorMessage.P0001.getCode());
    }
}
