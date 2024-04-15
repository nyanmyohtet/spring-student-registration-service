package com.nyanmyohtet.studentregistrationservice.api.rest;
import com.nyanmyohtet.studentregistrationservice.annotation.CustomAnnotation;
import com.nyanmyohtet.studentregistrationservice.api.request.StudentDto;
import com.nyanmyohtet.studentregistrationservice.api.response.StudentResponse;
import com.nyanmyohtet.studentregistrationservice.service.StudentService;
import com.nyanmyohtet.studentregistrationservice.util.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CustomAnnotation("someValue")
@RestController
@RequestMapping(path = "/api/v1/student-management/students")
public class StudentRestController {

    @Autowired
    StudentService studentService;

    // TODO: add more..
    @GetMapping
    public ResponseEntity<StudentResponse> getAllStudent(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {

        StudentResponse allStudents = studentService.getAllStudents(name, pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping("/stored-procedure")
    public ResponseEntity<List<StudentDto>> getAllStudentByStoredProcedure(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize) {

        List<StudentDto> allStudents = studentService.getAllStudentsByStoredProcedure(pageNo, pageSize);
        return new ResponseEntity<>(allStudents, HttpStatus.OK);
    }

    @GetMapping(path = "/{studentId}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable(name = "studentId") Long studentId) {
        StudentDto student = studentService.getStudentById(studentId);
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody @Valid StudentDto studentDto) {
        StudentDto student = studentService.createStudent(studentDto);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{studentId}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable(name = "studentId") Long studentId, @RequestBody @Valid StudentDto studentDto) {
        StudentDto student = studentService.updateStudent(studentId, studentDto);
        return ResponseEntity.ok(student);
    }

    @DeleteMapping(path = "/{studentId}")
    public void deleteStudent(@PathVariable(name = "studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }
}