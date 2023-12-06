package com.mia.xrs.dto;

import com.mia.xrs.entity.Letter;
import com.mia.xrs.entity.User;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RejectDto {
    private Integer id;

    private String routeNo;

    private Date returnDate;

    private Letter letter;

    private String rejectReason;

    private User returner;

    private User receiver;

    private String returnerSignature;

    private String receiverSignature;
}
