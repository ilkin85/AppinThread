package com.mia.xrs.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LetterDto {
    private Integer id;

    private Integer letterNo;

    private String importanceDegree;

    private String note;

    private Date date;

    private Boolean status;
}
