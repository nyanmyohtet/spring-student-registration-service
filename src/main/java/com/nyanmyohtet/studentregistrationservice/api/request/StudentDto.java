package com.nyanmyohtet.studentregistrationservice.api.request;

import lombok.Data;

@Data
public class StudentDto {
    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String address;
}
