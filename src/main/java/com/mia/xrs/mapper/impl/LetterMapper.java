package com.mia.xrs.mapper.impl;

import com.mia.xrs.dto.DepartmentDto;
import com.mia.xrs.dto.LetterDto;
import com.mia.xrs.entity.Letter;
import com.mia.xrs.mapper.Mapper;
import org.springframework.stereotype.Component;

@Component
public class LetterMapper implements Mapper<Letter, LetterDto> {

    @Override
    public LetterDto toDto(Letter letter) {
        return LetterDto.builder()
                .id(letter.getId())
                .letterNo(letter.getLetterNo())
                .fromDepartment(DepartmentDto.builder()
                        .id(letter.getFromDepartment().getId())
                        .name(letter.getFromDepartment().getName())
                        .parentId(letter.getFromDepartment().getParentId())
                        .build())
                .toDepartment(DepartmentDto.builder()
                        .id(letter.getFromDepartment().getId())
                        .name(letter.getFromDepartment().getName())
                        .parentId(letter.getFromDepartment().getParentId())
                        .build())
                .importanceDegree(letter.getImportanceDegree())
                .date(letter.getDate())
                .note(letter.getNote())
                .build();
    }

    @Override
    public Letter toPackage(LetterDto letterDto) {
        return null;
    }
}
