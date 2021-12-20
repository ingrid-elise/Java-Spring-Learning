package com.example.demo.student.service;

// import java.time.LocalDate;
// import java.time.Month;
import java.util.List;
import java.util.Optional;

import com.example.demo.student.EmailNotExistsException;
import com.example.demo.student.entity.Student;
import com.example.demo.student.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service // Spring bean 
// @Service is the same as @Component
public class StudentService {
     
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List <Student> getStudents() {
		return studentRepository.findAll();
	}

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
        .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new EmailNotExistsException();
        } else {
            studentRepository.save(student);
        }
    }

    public boolean deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        studentRepository.deleteById(studentId);
        return exists;
    }

}

// findAll() returns a list to us from our data base
// throw new IllegalStateException("email taken") is thrown when a method is called when it shouldn't