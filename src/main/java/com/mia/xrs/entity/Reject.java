package com.mia.xrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String routeNo;

    private Timestamp returnDate;

    @OneToOne
    @JoinColumn(name = "letter_id")
    private Letter letter;

//    @OneToOne
//    private Department fromDepartment;

//    @OneToOne
//    private Department toDepartment;

    private String rejectReason;

    @OneToOne
    private User returner;

    @OneToOne
    private User receiver;

    private String returnerSignature;

    private String receiverSignature;
}
