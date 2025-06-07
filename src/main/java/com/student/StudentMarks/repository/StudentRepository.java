package com.student.StudentMarks.repository;

import com.student.StudentMarks.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
