package com.tpe.repository;

import com.tpe.domain.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {


    boolean existsByEmail(String email);

    //public Pageable getStudentsPageable(Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.lastName=:lName")
    public List<Student> findStudentsByField(@Param("lName") String lName);
}
