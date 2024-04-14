package com.nyanmyohtet.studentregistrationservice.service.Impl;

import com.nyanmyohtet.studentregistrationservice.api.request.StudentDto;
import com.nyanmyohtet.studentregistrationservice.api.response.StudentResponse;
import com.nyanmyohtet.studentregistrationservice.exception.ResourceNotFoundException;
import com.nyanmyohtet.studentregistrationservice.persistence.model.Student;
import com.nyanmyohtet.studentregistrationservice.persistence.repository.StudentRepository;
import com.nyanmyohtet.studentregistrationservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public StudentResponse getAllStudents(String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // TODO: make flexible
        Page<Student> students = studentRepository.findAllByFirstName(name, pageable);

        // get content for page object
        List<Student> listOfStudents = students.getContent();

        List<StudentDto> content = listOfStudents.stream().map(this::mapToDTO).collect(Collectors.toList());

        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setContent(content);
        studentResponse.setPageNo(students.getNumber());
        studentResponse.setPageSize(students.getSize());
        studentResponse.setTotalElements(students.getTotalElements());
        studentResponse.setTotalPages(students.getTotalPages());
        studentResponse.setLast(students.isLast());

        return studentResponse;
    }

    @Override
    @Transactional
    public List<StudentDto> getAllStudentsByStoredProcedure(int pageNo, int pageSize) {
        List<Student> students = studentRepository.getStudents(pageNo, pageSize);

        List<StudentDto> studentDtos = students.stream().map(this::mapToDTO).collect(Collectors.toList());

        return studentDtos;
    }

    @Override
    public StudentDto getStudentById(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isEmpty()) {
            throw new ResourceNotFoundException("student not found");
        }

        Student student = studentOptional.get();

        return mapToDTO(student);
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        // convert DTO to entity
        Student student = mapToEntity(studentDto);
        Student newStudent = studentRepository.save(student);

        // convert entity to DTO
        return mapToDTO(newStudent);
    }

    @Override
    public StudentDto updateStudent(Long studentId, StudentDto studentExistingDto) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isEmpty()) {
            throw new ResourceNotFoundException("student not found");
        }

        // Student student = studentOptional.get();

        Student student = mapToEntity(studentExistingDto);
        student.setId(studentId);
        Student updatedStudent = studentRepository.save(student);

        return mapToDTO(updatedStudent);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);

        if (studentOptional.isEmpty()) {
            throw new ResourceNotFoundException("student not found");
        }

        Student student = studentOptional.get();
        studentRepository.delete(student);
    }

    // convert Entity into DTO
    private StudentDto mapToDTO(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setAddress(student.getAddress());
        return studentDto;
    }

    // convert DTO to entity
    private Student mapToEntity(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setAddress(studentDto.getAddress());
        return student;
    }
}
