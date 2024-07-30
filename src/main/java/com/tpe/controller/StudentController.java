package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger("StudentController.class");

    @Autowired
    private StudentService studentService;

    @GetMapping
    public ResponseEntity<List<Student>> findAll() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Map<String, String>>createStudent (@Valid @RequestBody Student student) {
        studentService.createStudent(student);
        Map<String, String> map = new HashMap<>();
        map.put("message", "Student created successfully");
        map.put("status", "true");
        return new ResponseEntity<>(map, HttpStatus.CREATED);

    }

    @GetMapping("/query") //http://localhost:8080/student/query?id=1  use this we have more than one parameter
    public  ResponseEntity<Student> getStudentById(@RequestParam("id") Long id) {
        Student student = studentService.getStudentsById(id);
        return ResponseEntity.ok(student);
    }

    // method to get student by id using pathVariable

//    @GetMapping("{id}") //http://localhost:8080/student/1
//    public  ResponseEntity<Student> getStudentByIdUsingPathVariable(@PathVariable("id") Long id) {
//        Student student = studentService.getStudentsById(id);
//        return ResponseEntity.ok(student);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<Map<String, String>> deleteStudent(@PathVariable("id") Long id) {
//        studentService.deleteStudentById(id);
//        Map<String, String> map = new HashMap<>();
//        map.put("message", "Student deleted successfully");
//        map.put("status", "true");
//        return new ResponseEntity<>(map, HttpStatus.OK); //return ResponseEntity.Ok(map)
//    }

//    @PutMapping("/{id}") //http://localhost:8080/student/1 + PUT + JSON
//    public ResponseEntity<String> updateStudent(@PathVariable("id") Long id,
//                                                @Valid @RequestBody StudentDTO studentDTO) {
//        studentService.updateStudent(id, studentDTO);
//        String message = "Student updated successfully";
//        return new ResponseEntity<>(message, HttpStatus.OK);
//
//    }

    //method to get student by pagination

    @GetMapping("/page")
    public ResponseEntity<Page<Student>> getAllStudents(
            @RequestParam("page") int page, //number of page
            @RequestParam("size") int size, // number of students each page
            @RequestParam("sort") String prop, // optional property for sort
            @RequestParam("direction") Sort.Direction direction // sorting direction ascending or descending
   ) {
       Pageable pageable =  PageRequest.of(page, size, Sort.by(direction, prop));
       Page<Student> studentPage = studentService.getAllStudentsByPage(pageable);
       return ResponseEntity.ok(studentPage);
    }

    @GetMapping("/lastname")
    public ResponseEntity<List<Student>> getStudentsByLastname(@RequestParam("lastname") String lName) {
        List<Student> students= studentService.findStudentsByField(lName);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome(HttpServletRequest request) {
        logger.warn("===============Wellcome {}", request.getServletPath());
        String mes = "Wellcome to Student contoller";
        return new ResponseEntity<>(mes, HttpStatus.OK);
    }


}
