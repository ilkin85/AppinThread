package com.mia.xrs.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RejectDto {

    private Integer id;

    private String routeNo;

    private Timestamp returnDate;

    private LetterDto letter;

    private String rejectReason;

    private UserDto returner;

    private UserDto receiver;

    private String returnerSignature;

    private String receiverSignature;
}
