package com.entity;

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
@Table(name="faculty_course")
public class FacultyCourse {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne
	@JoinColumn(name="faculty_id", nullable=false)
	private Faculty faculty;
	
	
	@ManyToOne
	@JoinColumn(name="course_id", nullable=false)
	private Course course;
	

}
