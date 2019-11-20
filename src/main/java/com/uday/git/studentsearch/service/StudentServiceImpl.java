package com.uday.git.studentsearch.service;

import com.uday.git.studentsearch.entity.Student;
import com.uday.git.studentsearch.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentRepository repository;

    @Override
    public List<Student> findAll() {
        return repository.findAll();
    }

    @Override
    public Page<Student> findAllByPaging(Pageable pageable){
        return repository.findAll(pageable);
    }

    @Override
    public Student save(Student employee) {
        return repository.save(employee);
    }

    @Override
    public void delete(long studentId) {
        repository.deleteById(studentId);
    }

    @Override
    public Page<Student> findStudentByNamePagingCriteria(String firstName, String lastName, Pageable pageable) {
        Page page = repository.findAll(new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(firstName!=null && lastName != null) {
                    predicates.add(criteriaBuilder.and(
                            criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"),
                            criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"))
                    );
                }else if(firstName != null){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%")));
                }else if(lastName != null){
                    predicates.add(criteriaBuilder.and(criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%")));
                }

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, pageable);

       // page.getTotalElements();        // get total elements
       // page.getTotalPages();           // get total pages
        return page;       // get List of Employee
        /*if(firstName!=null && lastName != null) {
            predicates.add(criteriaBuilder.and(
                    criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"),
                    criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"))
            );
        }else if(firstName != null){
            predicates.add(criteriaBuilder.like(root.get("firstName"), "%" + firstName + "%"));
        }else if(lastName != null){
            predicates.add(criteriaBuilder.like(root.get("lastName"), "%" + lastName + "%"));
        }*/
    }

    @Override
    public List<Student> findByCriteria(Pageable pageable) {
        return null;
    }
}
