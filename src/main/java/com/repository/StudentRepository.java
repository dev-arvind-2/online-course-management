package com.repository;

import java.util.List;

import com.entity.Student;


public interface StudentRepository {
	
	void saveStudent(Student student);
	
	void updateStudent(Student student);
	
	void deleteStudent(int studentId);
	
	Student getStudentById(int studentId);
	
	
	List<Student> getAllStudents();
	
	
	


}
