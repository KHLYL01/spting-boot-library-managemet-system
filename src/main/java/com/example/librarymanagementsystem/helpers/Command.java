package com.example.librarymanagementsystem.helpers;

import org.springframework.http.ResponseEntity;

public interface Command<INPUT,OUTPUT>{
    ResponseEntity<OUTPUT> execute(INPUT input);
}
