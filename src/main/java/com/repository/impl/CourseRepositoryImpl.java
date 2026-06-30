package com.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Course;
import com.entity.Student;
import com.exception.DatabaseOperationException;
import com.exception.ResourceNotFoundException;
import com.repository.CourseRepository;
import com.util.HibernateUtil;

public class CourseRepositoryImpl implements CourseRepository{

	@Override
	public void saveCourse(Course course) {
	
		Transaction tx=null;
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			tx=session.beginTransaction();
			session.save(course);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			
			throw new DatabaseOperationException("Failed to Save Faculty", e);

		}
		
	}
	
	
	
	
	
	
	@Override
	public void updateCourse(Course course) {

	    Transaction tx = null;

	    try(Session session =
	            HibernateUtil.getSessionFactory().openSession()) {

	        tx = session.beginTransaction();

	        Course existingCourse =session.get(Course.class,course.getCourseId());

	        if(existingCourse == null) {

	            throw new ResourceNotFoundException("Course not found with id : "+ course.getCourseId());
	        }

	        // Update only provided fields

	        if(course.getCourseCode() != null) {
	            existingCourse.setCourseCode(course.getCourseCode());
	        }

	        if(course.getCourseName() != null) {
	            existingCourse.setCourseName(course.getCourseName());
	        }

	        if(course.getDuration() > 0) {
	            existingCourse.setDuration(course.getDuration());
	        }

	        if(course.getFees() > 0) {
	            existingCourse.setFees(course.getFees());
	        }

	        session.update(existingCourse);

	        tx.commit();

	    } catch(Exception e) {

	        if(tx != null) {
	            tx.rollback();
	        }

	        throw e;
	    }
	}
	
	
	
	@Override
	public void deleteCourse(int courseId) {
		
		Transaction tx=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			tx=session.beginTransaction();
			Course c=session.get(Course.class,courseId);
			
			if(c==null)
			{
				throw new ResourceNotFoundException("Course Not Found with id : "+courseId);
			}
		
			session.delete(c);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			
			throw e;
		}
		
	}

	@Override
	public Course getCourseId(int courseId) {
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			Course course= session.get(Course.class, courseId);
			
			if(course==null)
			{
				throw new ResourceNotFoundException("Course Not Found with id : "+courseId);
			}
			
			
			return course;
			
		}catch(ResourceNotFoundException e) {
			throw e;
			
		}catch(Exception e) {
			throw new DatabaseOperationException("Failed to fectch Course", e);
		}
	}
	
	
	
	

	@Override
	public List<Course> getAllCourses() {
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			return session.createQuery("from Course",Course.class).list();
			
		}catch(DatabaseOperationException e) {
			throw new DatabaseOperationException("Failed to fetch course", e);
		}
		
	}







	@Override
	public long countCourse() {
		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {

	            return (Long) session.createQuery(
	                "select count(s.id) from Course s"
	            ).uniqueResult();
	            
	            
		 }catch(DatabaseOperationException e) {
	        	throw new DatabaseOperationException("Unable to retrive Course count", e);
	      }
	}
	
	
	

}
