package com.service.impl;

import java.util.List;

import com.entity.Enrollment;
import com.repository.EnrollmentRepository;
import com.repository.StudentRepository;
import com.repository.impl.EnrollmentRepositoryImpl;
import com.repository.impl.StudentRepositoryImpl;
import com.service.EnrollmentService;

public class EnrollmentServiceImpl implements EnrollmentService {

    private EnrollmentRepository enrollmentRepository =
            new EnrollmentRepositoryImpl();
    
	  private EnrollmentRepository enrollmentRepositoryReport = new EnrollmentRepositoryImpl();


    @Override
    public void enrollStudent(int studentId, int courseId) {
        enrollmentRepository.enrollStudent(studentId, courseId);
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.getAllEnrollments();
    }

    @Override
    public void deleteEnrollment(int enrollmentId) {
        enrollmentRepository.deleteEnrollment(enrollmentId);
    }

    @Override
    public List<Enrollment> getStudentsByCourse(int courseId) {
        return enrollmentRepository.getStudentsByCourse(courseId);
    }
    
    @Override
    public List<Enrollment> getCoursesByStudent(int studentId) {
        return enrollmentRepository.getCoursesByStudent(studentId);
    }

	@Override
	public long getTotalEnrollments() {
        return enrollmentRepositoryReport.countEnrollment();

	}
}