package com.nyanmyohtet.studentregistrationservice.persistence.model;

import com.nyanmyohtet.studentregistrationservice.converter.EncryptedStringConverter;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "address", nullable = false)
    @Convert(converter = EncryptedStringConverter.class)
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
