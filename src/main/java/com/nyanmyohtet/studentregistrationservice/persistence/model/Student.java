package com.nyanmyohtet.studentregistrationservice.persistence.model;

import com.nyanmyohtet.studentregistrationservice.converter.AttributeEncryptor;
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

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address", nullable = false)
    @Convert(converter = AttributeEncryptor.class)
    private String address;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
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
