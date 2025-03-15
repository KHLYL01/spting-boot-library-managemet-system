package com.example.librarymanagementsystem.feature.book.model.entity;


import com.example.librarymanagementsystem.feature.borrowRecord.model.entity.BorrowRecord;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private int publicationYear;
    private String isbn;

    @OneToMany(mappedBy = "book",cascade = CascadeType.REMOVE)
    private List<BorrowRecord> borrowRecords;

}
