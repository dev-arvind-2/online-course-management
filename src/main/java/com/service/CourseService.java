package com.service;

import java.util.List;
import com.entity.Course;

public interface CourseService {

    void addCourse(Course course);

    void updateCourse(Course course);

    void deleteCourse(int id);

    Course getCourseById(int id);

    List<Course> getAllCourses();
    
    
    
//    =========================================================
    long getTotalCourses();
}