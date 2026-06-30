package com.repository;

import java.util.List;

import com.entity.Faculty;

public interface FacultyRepository {
	
	void saveFaculty(Faculty faculty);
	
	void updateFaculty(Faculty faculty);
	
	void deleteFaculty(int facultyId);
	
	Faculty getFacultyById(int facultyId);
	
	List<Faculty> getAllFaculty();
	
	
//	=========================================================
//	 long countFaculty();

}
