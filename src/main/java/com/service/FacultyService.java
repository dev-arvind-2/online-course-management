package com.service;

import java.util.List;
import com.entity.Faculty;

public interface FacultyService {

    void addFaculty(Faculty faculty);

    void updateFaculty(Faculty faculty);

    void deleteFaculty(int id);

    Faculty getFacultyById(int id);

    List<Faculty> getAllFaculties();
    
    
    
//    ==================================================
//    long getTotalFaculty();


}