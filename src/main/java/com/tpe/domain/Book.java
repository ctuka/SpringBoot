package com.tpe.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.persistence.*;


@Setter
@Getter
@Entity
@Data
@Table(name = "tbl_book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonProperty("bookName")  //it show as json type
    private String title;

    private String authors;
    private String publisher;
    private String publishedDate;
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore // it will not create field for student in json of book item
    private Student student;

}
