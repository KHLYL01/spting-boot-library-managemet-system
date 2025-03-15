package com.example.librarymanagementsystem.feature.patron.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdatePatronDto {
    private Long id;
    private PatronRequestDto patronRequestDto;
}
