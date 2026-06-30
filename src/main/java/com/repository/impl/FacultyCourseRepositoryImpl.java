package com.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.FacultyCourse;
import com.exception.DatabaseOperationException;
import com.exception.ResourceNotFoundException;
import com.repository.FacultyCourseRepository;
import com.util.HibernateUtil;

public class FacultyCourseRepositoryImpl implements FacultyCourseRepository{

	@Override
	public void saveFacultyCourse(FacultyCourse fc) {
		
		Transaction tx=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			tx=session.beginTransaction();
			
			session.save(fc);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			
			throw new DatabaseOperationException("Failed to Save Student", e);

		}
	}
	
	
	

	@Override
	public void deleteFacultyCourse(int id) {
		
		
		Transaction tx=null;
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			tx=session.beginTransaction();
			
			FacultyCourse fc=session.get(FacultyCourse.class, id);
			
			
		    if (fc != null) {

	            session.delete(fc);

	            tx.commit();

	            System.out.println("Assignment removed successfully.");
	        } else {

//	            System.out.println("Assignment not found.");
	            
				throw new ResourceNotFoundException("Assignment not found.");

	        }
			
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			
			throw e;
		}
	}

	
	
	@Override
	public FacultyCourse getById(int id) {
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			FacultyCourse facultyCourse=session.get(FacultyCourse.class, id);
			
			if(facultyCourse==null)
			{
				throw new ResourceNotFoundException("FacultyCourse Not Found with id : "+id);
			}
			
			return facultyCourse;
			
		}catch(ResourceNotFoundException e) {
			throw e;
			
		}catch(Exception e) {
			throw new DatabaseOperationException("Failed to fectch FacultyCourse", e);
		}
	}

	
	
	
	@Override
	public List<FacultyCourse> getAll() {
	
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			return session.createQuery("from FacultyCourse",FacultyCourse.class).list();
			
		}catch(DatabaseOperationException e) {
			throw new DatabaseOperationException("Failed to fetch FacultyCourse", e);
		}
	}
	
	
	
	

}
