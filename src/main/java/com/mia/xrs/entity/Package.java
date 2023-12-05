package com.mia.xrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Package {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer packageNo;

    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private Boolean status;

    private Date date;

    @OneToOne
    private Department fromDepartment;

    @OneToOne
    private Department toDepartment;

    private String note;

    private String signature;

    @OneToMany
    private List<Letter> letters;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;
}
