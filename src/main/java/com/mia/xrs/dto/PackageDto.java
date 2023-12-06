package com.mia.xrs.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDto {

    private Integer id;

    private FormDto form;

    private Integer packageNo;

    private List<LetterDto> letters;

    private Timestamp sentDate;

    private Timestamp receiveDate;

    private String envelope;

    private String parcel;

    private String senderSignature;

    private String receiverSignature;

    private Integer letterCount;
}
