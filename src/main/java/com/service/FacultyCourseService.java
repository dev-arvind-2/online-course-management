package com.service;

import java.util.List;
import com.entity.FacultyCourse;

public interface FacultyCourseService {
	
	

    void assignFacultyToCourse(int facultyId, int courseId);

    List<FacultyCourse> getAllAssignments();

    void removeAssignment(int assignmentId);
}