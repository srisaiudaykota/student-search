package com.uday.git.studentsearch.repository;

import com.uday.git.studentsearch.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public interface StudentRepository extends JpaRepository<Student, Long> , JpaSpecificationExecutor<Student> {
    List<Student> findStudentByFirstNameOrLastName(String firstName, String lastName, Pageable pageable);
}
