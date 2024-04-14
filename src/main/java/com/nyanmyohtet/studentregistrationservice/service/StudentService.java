package com.nyanmyohtet.studentregistrationservice.service;

import com.nyanmyohtet.studentregistrationservice.api.request.StudentDto;
import com.nyanmyohtet.studentregistrationservice.api.response.StudentResponse;

import java.util.List;

public interface StudentService {
    StudentResponse getAllStudents(String name, int pageNo, int pageSize, String sortBy, String sortDir);

    List<StudentDto> getAllStudentsByStoredProcedure(int pageNo, int pageSize);

    StudentDto getStudentById(Long studentId);

    StudentDto createStudent(StudentDto productNewDto);

    StudentDto updateStudent(Long studentId, StudentDto productExistingDto);

    void deleteStudent(Long studentId);
}
