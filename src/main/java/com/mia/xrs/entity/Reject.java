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

    private String rejectReason;

    @ManyToOne
    @JoinColumn(name="returner_id")
    private User returner;

    @ManyToOne
    @JoinColumn(name="receiver_id")
    private User receiver;

    private String returnerSignature;

    private String receiverSignature;
}
