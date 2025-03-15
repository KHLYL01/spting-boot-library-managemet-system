package com.example.librarymanagementsystem.feature.book.repository;

import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
}
