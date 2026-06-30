package com.repository;

import java.util.List;

import com.entity.Enrollment;

public interface EnrollmentRepository {
	
	
	public void enrollStudent(int studentId, int courseId);
	
	public List<Enrollment> getAllEnrollments();
	
	public void deleteEnrollment(int enrollmentId);
	
	
//	=======================================================
	List<Enrollment> getStudentsByCourse(int courseId);

	List<Enrollment> getCoursesByStudent(int studentId);
	
	
//	=======================================================
	 long countEnrollment();

	
}
