package com.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "course")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;

    @NotBlank(message = "Course code cannot be empty")
    @Size(max = 20, message = "Course code cannot exceed 20 characters")
    @Column(nullable = false, length = 20)
    private String courseCode;

    @NotBlank(message = "Course name cannot be empty")
    @Size(max = 100, message = "Course name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String courseName;

    @Min(value = 1, message = "Duration must be greater than 0")
    @Max(value = 60, message = "Duration cannot exceed 60 months")
    @Column(nullable = false)
    private int duration;

    @Positive(message = "Fees must be greater than 0")
    @Column(nullable = false)
    private double fees;
}