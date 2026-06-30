package com.repository.impl;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.entity.Course;
import com.entity.Enrollment;
import com.entity.Student;
import com.exception.DatabaseOperationException;
import com.exception.DuplicateResourceException;
import com.exception.ResourceNotFoundException;
import com.repository.EnrollmentRepository;
import com.util.HibernateUtil;

public class EnrollmentRepositoryImpl implements EnrollmentRepository {

	
	
//  ===============================================================

	@Override
	public void enrollStudent(int studentId, int courseId) {

	    Transaction tx = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {

	        tx = session.beginTransaction();

	        Student student = session.get(Student.class, studentId);
	        Course course = session.get(Course.class, courseId);

	        if (student == null) {
//	            System.out.println("Student Not Found.");
//	            return;
	        	 throw new ResourceNotFoundException(
	                        "Student not found with id: " + studentId);
	        }

	        if (course == null) {
//	            System.out.println("Course Not Found.");
//	            return;
	        	  throw new ResourceNotFoundException(
	                        "Course not found with id: " + courseId);
	        }

	        Enrollment existingEnrollment = session.createQuery(
	                "FROM Enrollment e WHERE e.student.studentId = :sid AND e.course.courseId = :cid",
	                Enrollment.class)
	                .setParameter("sid", studentId)
	                .setParameter("cid", courseId)
	                .uniqueResult();

	        if (existingEnrollment != null) {
//	            System.out.println("Student Already Enrolled In This Course.");
//	            return;
	        	 throw new DuplicateResourceException("Student already enrolled in this course");
	        }

	        Enrollment enrollment = new Enrollment();
	        enrollment.setStudent(student);
	        enrollment.setCourse(course);
	        enrollment.setEnrollmentDate(LocalDate.now()); // ✅ FIX


	        session.persist(enrollment);

	        tx.commit();

	        System.out.println("Enrollment Successful.");
	    
	    
	    } catch (ResourceNotFoundException | DuplicateResourceException e) {
            if (tx != null) tx.rollback();
            throw e;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DatabaseOperationException(
                    "Error while enrolling student", e);
        }
	}
    
    
    
//    ===============================================================
    @Override
    public List<Enrollment> getAllEnrollments() {

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("FROM Enrollment",Enrollment.class).list();
            
            
        } catch (Exception e) {
            throw new DatabaseOperationException(
                    "Error fetching all enrollments", e);
        }
        
    }

    
    
    
//  ===============================================================

    @Override
    public void deleteEnrollment(int enrollmentId) {

        Transaction tx = null;

        try (Session session =
                HibernateUtil.getSessionFactory().openSession()) {

            tx = session.beginTransaction();

            Enrollment enrollment =
                    session.get(Enrollment.class, enrollmentId);

            if (enrollment == null) {
//                System.out.println("Enrollment Not Found.");
//                return;
            	
            	  throw new ResourceNotFoundException("Enrollment not found with id: " + enrollmentId);
            }

            session.delete(enrollment);
            tx.commit();

            System.out.println("Enrollment Deleted Successfully.");

            
            
        } catch (ResourceNotFoundException e) {
            if (tx != null) tx.rollback();
            throw e;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw new DatabaseOperationException(
                    "Error deleting enrollment", e);
        }
        
    }
    
    
    
//    ==============  This logic for course vise get student main Case 7=======================================================
    @Override
    public List<Enrollment> getStudentsByCourse(int courseId) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Enrollment e WHERE e.course.courseId = :cid",
                    Enrollment.class)
                    .setParameter("cid", courseId)
                    .list();
            
            
        } catch (Exception e) {
            throw new DatabaseOperationException(
                    "Error fetching students by course", e);
        }
       
    }
    
    
    
    
    
    
//  ==============  This logic for Student vise get Course main Case 6=======================================================
    @Override
    public List<Enrollment> getCoursesByStudent(int studentId) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery(
                    "FROM Enrollment e WHERE e.student.studentId = :sid",
                    Enrollment.class)
                    .setParameter("sid", studentId)
                    .list();
            
        } catch (Exception e) {
            throw new DatabaseOperationException(
                    "Error fetching courses by student", e);
        }
        
    }



    
//    =====================================================================
	@Override
	public long countEnrollment() {
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {

	            return (Long) session.createQuery(
	                "select count(s.id) from Enrollment s"
	            ).uniqueResult();
	            
		   } catch (Exception e) {
	            throw new DatabaseOperationException(
	                    "Error counting enrollments", e);
	        }
	}
    
}