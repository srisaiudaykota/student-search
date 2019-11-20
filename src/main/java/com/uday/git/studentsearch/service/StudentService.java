package com.uday.git.studentsearch.service;

import com.uday.git.studentsearch.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StudentService {
    List<Student> findAll();
    Page<Student> findAllByPaging(Pageable pageable);
    Student save(Student employee);
    void delete(long studentId);
    Page<Student> findStudentByNamePagingCriteria(String firstName, String lastName, Pageable pageable);
    List<Student> findByCriteria(Pageable pageable);
}
