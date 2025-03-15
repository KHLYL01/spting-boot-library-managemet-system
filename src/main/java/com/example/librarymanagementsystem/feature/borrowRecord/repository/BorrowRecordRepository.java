package com.example.librarymanagementsystem.feature.borrowRecord.repository;

import com.example.librarymanagementsystem.feature.borrowRecord.model.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    @Query("SELECT br FROM BorrowRecord br " +
            "WHERE br.book.id = :bookId " +
            "AND br.patron.id = :patronId " +
            "AND br.returnDate IS NULL"
    )
    Optional<BorrowRecord> findBorrowRecord(@Param(value = "bookId") Long bookId,
                                            @Param(value = "patronId") Long patronId);


    @Query("SELECT COUNT(br) = 0 FROM BorrowRecord br " +
            "WHERE br.book.id = :bookId " +
            "AND br.returnDate IS NULL")
    boolean checkBookIsFoundOnLibrary(@Param(value = "bookId") Long bookId);

}
