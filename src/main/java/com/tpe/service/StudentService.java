package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.dto.StudentDTO;
import com.tpe.exception.ConflictException;
import com.tpe.exception.ResourceNotFoundException;
import com.tpe.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void createStudent(Student student) {

        if (  studentRepository.existsByEmail(student.getEmail())) {
            throw new ConflictException("Email already exists");
        }
        studentRepository.save(student);
    }

    public Student getStudentsById(Long id) {
        return studentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("No student wit ID :" + id));
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public void updateStudent(Long id, StudentDTO studentDTO) {
        Student student = getStudentsById(id);
        boolean isEmailExist = studentRepository.existsByEmail(studentDTO.getEmail());

        if (isEmailExist && !studentDTO.getEmail().equals(student.getEmail())) {
            throw new ConflictException("Email already exists");
        }

        Student updatedStudent = studentDTO.convertStudentDTOToStudent(studentDTO);
        studentRepository.save(updatedStudent);
    }

    public Page<Student> getAllStudentsByPage(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }


    public List<Student> findStudentsByField(@Param("lName") String lName) {

        return  studentRepository.findStudentsByField(lName);
    }
}
