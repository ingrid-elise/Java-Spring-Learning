package com.example.demo.student.repository;

import java.util.Optional;

import com.example.demo.student.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    // custom function to find a user by email
    @Query("SELECT s FROM Student s WHERE s.email = ?1") // OR
    Optional<Student> findStudentByEmail(String email); // findStudentByEmail translates to SELECT * FROM student WHERE email = <whatever we pass in>
}

// inside the <> we are specifying 2 things
// 1. the type of object we want this repository to work with
// 2. and also the ID for the type that we want (Long, is the ID for student)

// this is the repository for the data access layer
// we want to use this inside of our 'service' (StudentService.java)
