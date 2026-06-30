package com.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name="student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;

    @NotBlank(message = "Name cannot be empty")
    @Size(max = 100, message = "Name must be less than 100 characters")
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank(message = "Email cannot be empty")
//    @Email(message = "Invalid email format")
    @Pattern(
    	    regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
    	    message = "Email must be in proper format (example: abc@gmail.com)"
    	)
    
    @Column(nullable = false, length = 100)
    private String email;

    @NotBlank(message = "Mobile cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be 10 digits")
    @Column(nullable = false, length = 15)
    private String mobile;

    @NotBlank(message = "City cannot be empty")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String city;
}