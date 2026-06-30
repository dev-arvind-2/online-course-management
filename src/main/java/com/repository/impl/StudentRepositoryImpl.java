package com.repository.impl;

import com.entity.Student;
import com.exception.DatabaseOperationException;
import com.exception.ResourceNotFoundException;
import com.repository.StudentRepository;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.util.HibernateUtil;

public class StudentRepositoryImpl implements StudentRepository{

	@Override
	public void saveStudent(Student student) {
		
		Transaction tx=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			tx=session.beginTransaction();
			session.save(student);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			
		
			throw new DatabaseOperationException("Failed to Save Student", e);
		}
		
	}
	
	
	
	
	   @Override
	    public void updateStudent(Student student) {

	        Transaction tx = null;

	        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

	            tx = session.beginTransaction();

	            Student existingStudent =session.get(Student.class,student.getStudentId());

	            if(existingStudent == null) {

	                throw new ResourceNotFoundException("Student not found with id : "+ student.getStudentId());
	            }

	            if(student.getName() != null) {
	                existingStudent.setName(student.getName());
	            }

	            if(student.getEmail() != null) {
	                existingStudent.setEmail(student.getEmail());
	            }

	            if(student.getMobile() != null) {
	                existingStudent.setMobile(student.getMobile());
	            }

	            if(student.getCity() != null) {
	                existingStudent.setCity(student.getCity());
	            }

	            session.update(existingStudent);
	            tx.commit();

	        } catch (Exception e) {

	            if(tx != null) {
	                tx.rollback();
	            }

	            throw e;
	        }
	    }
	
	

	@Override
	public void deleteStudent(int studentId) {
		
		Transaction tx=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			tx=session.beginTransaction();
			Student s=session.get(Student.class,studentId);
			
			if(s==null)
			{
				throw new ResourceNotFoundException("Student Not Found with id : "+studentId);
			}
			
			
			session.delete(s);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null)
			   tx.rollback();
			
			
			throw e;
		}
		
	}

	@Override
	public Student getStudentById(int studentId) {
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			Student student= session.get(Student.class, studentId);
			
			if(student==null)
			{
				throw new ResourceNotFoundException("Student Not Found with id : "+studentId);
			}
			
			
			return student;
			
			
		}catch(ResourceNotFoundException e) {
			throw e;
			
		}catch(Exception e) {
			throw new DatabaseOperationException("Failed to fectch student", e);
		}
	}
	
	
	
	
	

	@Override
	public List<Student> getAllStudents() {
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			return session.createQuery("from Student",Student.class).list();
			
		}catch(DatabaseOperationException e) {
			throw new DatabaseOperationException("Failed to fetch students", e);
		}
	}
	

	
	
	

	

}
