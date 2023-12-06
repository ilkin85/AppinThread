package com.mia.xrs.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto {

    private Integer id;

    private String name;

    private Integer parentId;
}
