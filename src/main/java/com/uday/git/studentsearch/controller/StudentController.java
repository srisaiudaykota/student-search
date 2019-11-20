package com.uday.git.studentsearch.controller;

import com.uday.git.studentsearch.entity.Student;
import com.uday.git.studentsearch.repository.StudentRepository;
import com.uday.git.studentsearch.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    public static final Integer PAGINATION_DEFAULT_SIZE=10;
    public static final Integer PAGINATION_DEFAULT_PAGE=0;

    /**
     * Used to fetch all students from DB
     *
     * */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
    public List<Student> getAllStudents(){
        return studentService.findAll();
    }
    /**
     * fetch students from DB having pagination
     *
     * */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json")
    Page<Student> getStudentsByPagination(@RequestParam(value = "page", required = false) int page,
                                          @RequestParam(value = "size", required = false) int size){
        page = page <0 ? PAGINATION_DEFAULT_PAGE : page;
        size = (size <0) ? PAGINATION_DEFAULT_SIZE: size;
        return studentService.findAllByPaging(PageRequest.of(page, size));
    }

    /**
     * Get Students matching name(First/Last Name)
    */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "/getByName", method = RequestMethod.GET, produces = "application/json")
    public Page<Student> getStudentsByFirstLastName(@RequestParam(value = "fName", required = false) String firstName,
                                                    @RequestParam(value = "lName", required = false) String lastName,
                                                    @RequestParam("page") int page,
                                                    @RequestParam("size") int size
                                                    ){
        page = page <0 ? PAGINATION_DEFAULT_PAGE : page;
        size = (size <0) ? PAGINATION_DEFAULT_SIZE: size;
        return studentService.findStudentByNamePagingCriteria(firstName, lastName, PageRequest.of(page, size));
    }

    /**
     * Insert Student Details into DB
     * */
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "/add")
    public ResponseEntity < String > addStudent(@RequestBody final Student student){
        Student addedStudent = studentService.save(student);
        return ResponseEntity.status(HttpStatus.CREATED).build();
                /*ResponseEntity
                .created(new URI(httpRe.getId().expand().getHref()))
                .body(addStudent);*/
    }

    /**
     * Delete Student  by ID
     * */
    @CrossOrigin(origins = "http://localhost:3000")
    @RequestMapping(value = "delete", method = RequestMethod.DELETE)        // or use @DeleteMapping
    public void delete(@RequestParam("id")long studentId){
        studentService.delete(studentId);
    }
}
