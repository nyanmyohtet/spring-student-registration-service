package com.nyanmyohtet.studentregistrationservice.persistence.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "books", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;
}
