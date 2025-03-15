package com.example.librarymanagementsystem.feature.patron.model.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PatronRequestDto {

    @NotNull(message = "Name Is Required")
    @NotEmpty(message = "Name must be not empty")
    private String name;

    @NotNull(message = "Contact Information Is Required")
    @NotEmpty(message = "Contact Information must be not empty")
    private String contactInformation;

}
