package com.service.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.entity.Course;
import com.entity.Student;
import com.exception.ValidationException;
import com.repository.CourseRepository;
import com.repository.StudentRepository;
import com.repository.impl.CourseRepositoryImpl;
import com.repository.impl.StudentRepositoryImpl;
import com.service.CourseService;
import com.util.ValidationUtil;


public class CourseServiceImpl implements CourseService{

	
	 private CourseRepository courseRepository;
	 
	 private CourseRepository CourseRepositoryReport = new CourseRepositoryImpl();

	
	public CourseServiceImpl()
	{
		this.courseRepository=new CourseRepositoryImpl();
	}
	
	
	@Override
	public void addCourse(Course course) {
		

		
//		--------------------------------------------- For Validation

		Set<ConstraintViolation<Course>> violations =
		        ValidationUtil.validate(course);

		if (!violations.isEmpty()) {

		    StringBuilder errorMsg = new StringBuilder();
		    errorMsg.append("\n Please correct the following errors:\n");

		    for (ConstraintViolation<Course> v : violations) {

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
		
		courseRepository.saveCourse(course);
		
	}

	@Override
	public void updateCourse(Course course) {
		courseRepository.updateCourse(course);
		
	}

	@Override
	public void deleteCourse(int id) {
		courseRepository.deleteCourse(id);
		
	}

	@Override
	public Course getCourseById(int id) {
		
		return courseRepository.getCourseId(id);
	}

	@Override
	public List<Course> getAllCourses() {
	
		return courseRepository.getAllCourses();
	}


	@Override
	public long getTotalCourses() {
        return CourseRepositoryReport.countCourse();

	}

}
