package com.example.demo.student.controller;

import java.util.List;

import javax.resource.spi.IllegalStateException;

import com.example.demo.student.entity.Student;
import com.example.demo.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;

// import com.example.demo.student.Student; //imports Student

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/student") // adds this onto localhost:8080
public class StudentController { // this will have all of the resources for our API

    private final StudentService studentService; // reference to StudentService then it is passed in to the StudentController

    @Autowired // this 'autowires' StudentService into StudentController constructor
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
	public List <Student> getStudents() {
		return studentService.getStudents();
	}

    @PostMapping // ADDING new resources
    public void registerNewStudent(@RequestBody Student student) throws IllegalStateException {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path ="{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) throws IllegalStateException{
        studentService.deleteStudent(studentId);
    }


}
