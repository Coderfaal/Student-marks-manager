package com.student.StudentMarks.controller;

import com.student.StudentMarks.repository.StudentRepository;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.*;
import com.student.StudentMarks.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);


    @PostMapping
    public ResponseEntity addStudent(@RequestBody  @Valid Student student) {
        logger.info("Received request to add student: {}", student.getName());
        studentRepository.save(student);
        logger.info("Student {} saved successfully", student.getName());
        return ResponseEntity.ok("Student created successfully");}

    @GetMapping
    public List<Student> getAllStudents() {
        logger.info("Fetching all students");
        List<Student> students = studentRepository.findAll();
        logger.info("Total students fetched: {}", students.size());
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable Long id) {
        logger.info("Fetching student with ID: {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            logger.info("Student found: {}", student.getName());
        } else {
            logger.warn("Student with ID {} not found", id);
        }
        return student;
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Long id) {
        logger.warn("Request to delete student with ID: {}", id);
        studentRepository.deleteById(id);
        logger.info("Deleted student with ID: {}", id);

    }

}
