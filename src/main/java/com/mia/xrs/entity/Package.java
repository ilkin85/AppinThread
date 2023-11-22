package com.mia.xrs.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.context.SecurityContextHolder;

import java.sql.Timestamp;

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

    private Double size;

    private Double packageFee;

    private String note;

    @CreationTimestamp
    @Column(updatable = false, columnDefinition = "TIMESTAMP DEFAULT now()")
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @ManyToOne
    private User createdBy;

    @ManyToOne
    private User updatedBy;

    @PrePersist
    public void prePersist() {
        setCreatedBy(getAuthenticatedUser());
    }

    @PreUpdate
    public void preUpdate() {
        setUpdatedBy(getAuthenticatedUser());
    }

    private User getAuthenticatedUser() {
        return (User) SecurityContextHolder
                          .getContext()
                          .getAuthentication()
                          .getPrincipal();
    }
}
