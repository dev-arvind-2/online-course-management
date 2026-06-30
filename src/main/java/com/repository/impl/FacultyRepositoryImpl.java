package com.repository.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Faculty;
import com.exception.DatabaseOperationException;
import com.exception.ResourceNotFoundException;
import com.repository.FacultyRepository;
import com.util.HibernateUtil;

public class FacultyRepositoryImpl implements FacultyRepository{

	@Override
	public void saveFaculty(Faculty faculty) {
		Transaction tx=null;
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			tx=session.beginTransaction();
			session.save(faculty);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			
			throw new DatabaseOperationException("Failed to Save Student", e);

		}
		
	}

	@Override
	public void updateFaculty(Faculty faculty) {

	    Transaction tx = null;

	    try(Session session =
	            HibernateUtil.getSessionFactory().openSession()) {

	        tx = session.beginTransaction();

	        Faculty existingFaculty =
	                session.get(Faculty.class,
	                        faculty.getFacultyId());

	        if(existingFaculty == null) {

	            throw new ResourceNotFoundException("Faculty not found with id : "+ faculty.getFacultyId());
	        }

	        // Update only provided fields

	        if(faculty.getName() != null) {
	            existingFaculty.setName(
	                    faculty.getName());
	        }

	        if(faculty.getEmail() != null) {
	            existingFaculty.setEmail(
	                    faculty.getEmail());
	        }

	        if(faculty.getSpecialization() != null) {
	            existingFaculty.setSpecialization(
	                    faculty.getSpecialization());
	        }

	        // Experience update
	        if(faculty.getExperience() > 0) {
	            existingFaculty.setExperience(
	                    faculty.getExperience());
	        }

	        session.update(existingFaculty);

	        tx.commit();

	    } catch(Exception e) {

	        if(tx != null) {
	            tx.rollback();
	        }

	        throw e;
	    }
	}
	@Override
	public void deleteFaculty(int facultyId) {
		
		Transaction tx=null;
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			tx=session.beginTransaction();
			Faculty f=session.get(Faculty.class, facultyId);
			
			if(f==null)
			{
				throw new ResourceNotFoundException("Faculty Not Found with id : "+facultyId);
			}
				
		    session.delete(f);
			tx.commit();
			
		}catch(Exception e) {
			if(tx!=null)
				tx.rollback();
			
			throw e;
		}
		
	}

	@Override
	public Faculty getFacultyById(int facultyId) {
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
			 Faculty faculty=session.get(Faculty.class, facultyId);
			 
			 if(faculty==null)
				{
					throw new ResourceNotFoundException("Faculty Not Found with id : "+facultyId);
				}
			 
			 return faculty;
			 
		}catch(ResourceNotFoundException e) {
			throw e;
			
		}catch(Exception e) {
			throw new DatabaseOperationException("Failed to fectch Faculty", e);

		}
	}

	
	
	@Override
	public List<Faculty> getAllFaculty() {
		
		try(Session session=HibernateUtil.getSessionFactory().openSession())
		{
		   return session.createQuery("from Faculty",Faculty.class).list();	
		   
		}catch(DatabaseOperationException e) {
			throw new DatabaseOperationException("Failed to fetch Faculty", e);

		}
	}

	
	
	
//	==========================================================
//	@Override
//	public long countFaculty() {
//		 try (Session session = HibernateUtil.getSessionFactory().openSession()) {
//
//	            return (Long) session.createQuery(
//	                "select count(f.facultyId) from Faculty f"
//	            ).uniqueResult();
//	            
//	            
//	        } catch(DatabaseOperationException e) {
//	        	throw new DatabaseOperationException("Unable to retrive Faculty count", e);
//	        }
//		 
//		 
//	}
//	
	


}
