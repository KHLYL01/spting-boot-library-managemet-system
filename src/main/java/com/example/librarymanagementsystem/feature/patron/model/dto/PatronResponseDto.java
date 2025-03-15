package com.example.librarymanagementsystem.feature.patron.model.dto;

import lombok.Data;

@Data
public class PatronResponseDto {
    private Long id;
    private String name;
    private String contactInformation;
}
