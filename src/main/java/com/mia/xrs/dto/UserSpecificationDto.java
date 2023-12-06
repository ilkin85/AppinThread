package com.mia.xrs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserSpecificationDto {
    private Integer id;

    private String firstName;

    private String lastName;

    private String  fatherName;

    private String policeCard;

    private String rank;

    private String note;
}
