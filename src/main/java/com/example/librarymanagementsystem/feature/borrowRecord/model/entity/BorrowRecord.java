package com.example.librarymanagementsystem.feature.borrowRecord.model.entity;

import com.example.librarymanagementsystem.feature.book.model.entity.Book;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "borrow_record")
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    private LocalDateTime borrowDate;

    private LocalDateTime returnDate;
}
