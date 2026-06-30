package com.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor


@Builder

@Entity
@Table(name="enrollment")
public class Enrollment {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int enrollmentId;
	
	
	@ManyToOne
	@JoinColumn(name="student_id", nullable=false)
	private Student student;
	
	
	@ManyToOne
	@JoinColumn(name="course_id", nullable=false)
	private Course course;
	
	@Column(nullable=false)
	private LocalDate enrollmentDate;

}
