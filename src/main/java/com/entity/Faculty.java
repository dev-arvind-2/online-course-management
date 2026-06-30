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
@Table(name = "faculty")
public class Faculty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int facultyId;

    @NotBlank(message = "Faculty name cannot be empty")
    @Size(max = 100, message = "Faculty name cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Email cannot be empty")
    @Pattern(
        regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
        message = "Email must be in proper format (example: abc@gmail.com)"
    )
    @Column(nullable = false, length = 100)
    private String email;

    @NotBlank(message = "Specialization cannot be empty")
    @Size(max = 100, message = "Specialization cannot exceed 100 characters")
    @Column(nullable = false, length = 100)
    private String specialization;

    @Min(value = 0, message = "Experience cannot be negative")
    @Max(value = 50, message = "Experience cannot exceed 50 years")
    @Column(nullable = false)
    private int experience;
}