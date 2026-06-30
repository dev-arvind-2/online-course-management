package com.service.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.entity.Student;
import com.exception.ValidationException;
import com.repository.StudentRepository;
import com.repository.impl.StudentRepositoryImpl;
import com.service.StudentService;
import com.util.ValidationUtil;

public class StudentServiceImpl implements StudentService {

	
	private StudentRepository studentRepository;
	
	  private StudentRepository studentRepositoryReport = new StudentRepositoryImpl();

	
	public StudentServiceImpl()
	{
		this.studentRepository=new StudentRepositoryImpl();
	}
	
	
	@Override
	public void addStudent(Student student) {
		
		
//		--------------------------------------------- For Validation

		Set<ConstraintViolation<Student>> violations =
		        ValidationUtil.validate(student);

		if (!violations.isEmpty()) {

		    StringBuilder errorMsg = new StringBuilder();
		    errorMsg.append("\n Please correct the following errors:\n");

		    for (ConstraintViolation<Student> v : violations) {

		        String field = v.getPropertyPath().toString();
		        String message = v.getMessage();

		        errorMsg.append("- ")
		                .append(field)
		                .append(" : ")
		                .append(message)
		                .append("\n");
		    }

		    throw new ValidationException(errorMsg.toString());
		}
		
//		--------------------------------------------- End Validation

        
		  studentRepository.saveStudent(student);
	}
	
	
	

	@Override
	public void updateStudent(Student student) {
		studentRepository.updateStudent(student);
	}
	

	@Override
	public void deleteStudent(int id) {
		studentRepository.deleteStudent(id);
	}

	
	@Override
	public Student getStudentById(int id) {
		
		return   studentRepository.getStudentById(id);
		
	}

	@Override
	public List<Student> getAllStudents() {
		
		return studentRepository.getAllStudents();
		
	}






}