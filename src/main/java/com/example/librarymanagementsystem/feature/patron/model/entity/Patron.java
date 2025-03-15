package com.example.librarymanagementsystem.feature.patron.model.entity;


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
@Table(name = "patron")
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactInformation;

    @OneToMany(mappedBy = "patron",cascade = CascadeType.REMOVE)
    private List<BorrowRecord> borrowRecords;

}
