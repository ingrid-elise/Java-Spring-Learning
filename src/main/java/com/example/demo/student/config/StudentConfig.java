package com.example.demo.student.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

// import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import com.example.demo.student.entity.Student;
import com.example.demo.student.repository.StudentRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // this is a bean
public class StudentConfig {
    
    @Bean
    InitializingBean addStudentRecords(StudentRepository repository) { // this is also a bean
        return () -> {
            // creating students to be saved into our database
            Student mariam = new Student(
                    "Mariam",
                    "mariam@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Student alex = new Student(
                    "Alex",
                    "alex@gmail.com",
                    LocalDate.of(2004, Month.JANUARY, 5)
            );

            // saves list of students
            repository.saveAll(
                    List.of(mariam, alex)
            );
        };
    }
}

// (StudentRepository repository) gives access to this repository