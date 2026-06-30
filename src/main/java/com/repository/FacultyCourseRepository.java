package com.repository;

import java.util.List;

import com.entity.FacultyCourse;

public interface FacultyCourseRepository {
	
	
	 void saveFacultyCourse(FacultyCourse fc);

	    void deleteFacultyCourse(int id);

	    FacultyCourse getById(int id);

	    List<FacultyCourse> getAll();
	
	

}
