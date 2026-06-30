package com.service.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.entity.Faculty;
import com.entity.Student;
import com.exception.ValidationException;
import com.repository.FacultyRepository;
import com.repository.impl.FacultyRepositoryImpl;
import com.service.FacultyService;
import com.util.ValidationUtil;



public class FacultyServiceImpl implements FacultyService{

	
	
	
	private FacultyRepository facultyRepository;
	
    private FacultyRepository facultyRepositoryReport = new FacultyRepositoryImpl();

	
	public FacultyServiceImpl() {
	    this.facultyRepository = new FacultyRepositoryImpl();
	   
	}
	
	
	@Override
	public void addFaculty(Faculty faculty) {
		
//		--------------------------------------------- For Validation
		Set<ConstraintViolation<Faculty>> violations =
		        ValidationUtil.validate(faculty);

		if (!violations.isEmpty()) {

		    StringBuilder errorMsg = new StringBuilder();
		    errorMsg.append("\n Please correct the following errors:\n");

		    for (ConstraintViolation<Faculty> v : violations) {

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
		
		
		facultyRepository.saveFaculty(faculty);
		
	}

	@Override
	public void updateFaculty(Faculty faculty) {
		facultyRepository.updateFaculty(faculty);
	}

	@Override
	public void deleteFaculty(int id) {
		facultyRepository.deleteFaculty(id);
		
	}

	@Override
	public Faculty getFacultyById(int id) {
		
		return facultyRepository.getFacultyById(id);
	}

	@Override
	public List<Faculty> getAllFaculties() {
		return facultyRepository.getAllFaculty();
	}


	
	
//	========================================================
//	 public long getTotalFaculty() {
//	        return facultyRepositoryReport.countFaculty();
//	    }

	
}
