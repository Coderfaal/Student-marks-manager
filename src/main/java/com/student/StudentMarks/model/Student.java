package com.student.StudentMarks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
@Id
@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int mathMarks;
    private int scienceMarks;
    private int englishMarks;
}
