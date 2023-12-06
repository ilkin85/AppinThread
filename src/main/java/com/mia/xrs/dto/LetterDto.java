package com.mia.xrs.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LetterDto {

    private Integer id;

    private Integer letterNo;

    private String importanceDegree;

    private Timestamp date;

    private DepartmentDto fromDepartment;

    private DepartmentDto toDepartment;

    private String note;
}
