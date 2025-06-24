package com.student.StudentMarks.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, message = "Name must be at least 2 characters")
    private String name;
    //@NotBlank(message= "mathMarks should not be blank")
    private int mathMarks;
   // @NotBlank(message= "scienceMarks should not be blank")
    private int scienceMarks;
    //@NotBlank(message= "englishMarks should not be blank")
    private int englishMarks;
}
