package com.service;

import java.util.List;
import com.entity.Enrollment;

public interface EnrollmentService {

    void enrollStudent(int studentId, int courseId);

    List<Enrollment> getAllEnrollments();

    void deleteEnrollment(int enrollmentId);

    
//    ========================================
    List<Enrollment> getStudentsByCourse(int courseId);
    
    List<Enrollment> getCoursesByStudent(int studentId);
    
    
    
//    =============================================
    long getTotalEnrollments();
}