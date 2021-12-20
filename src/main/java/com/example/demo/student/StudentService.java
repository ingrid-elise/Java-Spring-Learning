package com.example.demo.student;

// import java.time.LocalDate;
// import java.time.Month;
import java.util.List;
import java.util.Optional;

import javax.resource.spi.IllegalStateException;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

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

    public void addNewStudent(Student student) throws IllegalStateException {
        Optional<Student> studentOptional = studentRepository
        .findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        } else {
            studentRepository.save(student);
        }
    }

    public void deleteStudent(Long studentId) throws IllegalStateException {
        boolean exists = studentRepository.existsById(studentId);
        if (!exists) { // if id doesn't exist then we want to ...
            throw new IllegalStateException("student with id" + studentId + "does not exist");
        } else { // if student id does exist we want to delete 
            studentRepository.deleteById(studentId);
        }
    }

}

// findAll() returns a list to us from our data base
// throw new IllegalStateException("email taken") is thrown when a method is called when it shouldn't