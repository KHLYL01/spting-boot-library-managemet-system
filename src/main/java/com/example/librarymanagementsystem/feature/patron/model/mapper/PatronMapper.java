package com.example.librarymanagementsystem.feature.patron.model.mapper;


import com.example.librarymanagementsystem.feature.patron.model.dto.PatronRequestDto;
import com.example.librarymanagementsystem.feature.patron.model.dto.PatronResponseDto;
import com.example.librarymanagementsystem.feature.patron.model.entity.Patron;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface PatronMapper {

    PatronResponseDto toDto(Patron entity);

    List<PatronResponseDto> toDtos(List<Patron> entities);

    @Mapping(target = "id",ignore = true)
    Patron toEntity(PatronRequestDto dto);

    @Mapping(target = "id",ignore = true)
    void toEntity(@MappingTarget Patron entity , PatronRequestDto dto);

}
