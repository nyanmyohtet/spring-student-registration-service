package com.nyanmyohtet.studentregistrationservice.persistence.repository;

import com.nyanmyohtet.studentregistrationservice.persistence.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Procedure(name = "getStudents")
    List<Student> getStudents(Integer pageNumber, Integer pageSize);

    Page<Student> findAllByName(String name, Pageable pageable);

}
