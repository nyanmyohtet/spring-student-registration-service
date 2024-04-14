package com.nyanmyohtet.studentregistrationservice.service;

import com.nyanmyohtet.studentregistrationservice.api.request.StudentDto;
import com.nyanmyohtet.studentregistrationservice.api.response.StudentResponse;

public interface StudentService {
    StudentResponse getAllStudents(String name, int pageNo, int pageSize, String sortBy, String sortDir);

    StudentDto getStudentById(Long studentId);

    StudentDto createStudent(StudentDto productNewDto);

    StudentDto updateStudent(Long studentId, StudentDto productExistingDto);

    void deleteStudent(Long studentId);
}
