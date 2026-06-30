package com.repository;

import java.util.List;

import com.entity.Course;

public interface CourseRepository {
	
	
	void saveCourse(Course course);
	
	void updateCourse(Course course);
	
	void deleteCourse(int courseId);
	
	Course getCourseId(int courseId);
	
	List<Course> getAllCourses();
	
		
//	============================================
	 long countCourse();

}
