package com.tpe.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.tpe.domain.Student;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private Long id;

    @NotNull(message = "First name can not be null.")
    @NotBlank(message = "First name can not be whitespace")
    @Size(min = 2, max = 25, message = "First name must be between {min} and {max} characters.")
    @Column(nullable = false, length = 25)
    private String name;
    private String lastName;
    private Integer grade;
    private String phoneNumber;

    @Email(message = "Provide valid email.")
    private String email;
    @Setter(AccessLevel.NONE)

    private LocalDateTime createDate = LocalDateTime.now();

//    public StudentDTO(Long id, String name, String lastName, Integer grade, String phoneNumber, String email, LocalDateTime createDate) {
//        this.id = id;
//        this.name = name;
//        this.lastName = lastName;
//        this.grade = grade;
//        this.phoneNumber = phoneNumber;
//        this.email = email;
//        this.createDate = createDate;
//    }

    public Student convertStudentDTOToStudent(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.getName());
        student.setLastName(studentDTO.getLastName());
        student.setGrade(studentDTO.getGrade());
        student.setPhoneNumber(studentDTO.getPhoneNumber());
        student.setEmail(studentDTO.getEmail());
        return student;

    }

    public StudentDTO convertStudenToStudentDTO(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName(student.getName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setGrade(student.getGrade());
        studentDTO.setPhoneNumber(student.getPhoneNumber());
        studentDTO.setEmail(student.getEmail());
        return studentDTO;

    }
}
