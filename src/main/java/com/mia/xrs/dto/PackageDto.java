package com.mia.xrs.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDto {

    private Integer id;

    private Integer uniqueId;

    private FormDto form;

    private Integer packageNo;

    private List<LetterDto> letters;

    private Timestamp sentDate;

    private Timestamp receiveDate;

    private String senderSignature;

    private String receiverSignature;

    private UserDto createdBy;

    private Integer letterCount;
}
