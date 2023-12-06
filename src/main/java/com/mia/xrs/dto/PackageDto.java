package com.mia.xrs.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PackageDto {
    private Integer id;

    private Integer packageNo;

    private Integer letterCount;

    private Boolean status;

    private Date sentDate;

    private Date receiveDate;

    private String envelope;

    private String parcel;

    private String senderSignature;

    private String receiverSignature;

    private String note;
}
