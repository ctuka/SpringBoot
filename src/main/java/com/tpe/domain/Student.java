package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_student")
public class Student {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull(message = "First name can not be null.")
    @NotBlank(message = "First name can not be whitespacd")
    @Size(min = 2, max = 50, message = "First name must be between {min} and {max} characters.")
    @Column(nullable = false, length = 25)
    private String name;
    private String lastName;
    private Integer grade;

    private String phoneNumber;
    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "Provide valid email.")
    private String email;
    @Setter(AccessLevel.NONE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy HH:mm:ss", timezone = "US")
    private LocalDateTime createDate = LocalDateTime.now();

}
