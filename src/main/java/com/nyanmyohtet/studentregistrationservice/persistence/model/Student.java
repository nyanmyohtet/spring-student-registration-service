package com.nyanmyohtet.studentregistrationservice.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column()
    private String firstName;

    @Column()
    private String lastName;

    @Column()
    private String address;

    @Column()
    private Date createdDate;

    @Column()
    private Date updatedDate;

    @PrePersist
    private void prePersist() {
        this.createdDate = Calendar.getInstance().getTime();
        this.updatedDate = Calendar.getInstance().getTime();
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedDate = Calendar.getInstance().getTime();
    }
}
