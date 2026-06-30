package com.service.impl;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.entity.Course;
import com.entity.Faculty;
import com.entity.FacultyCourse;
import com.exception.ValidationException;
import com.repository.CourseRepository;
import com.repository.FacultyCourseRepository;
import com.repository.FacultyRepository;
import com.repository.impl.CourseRepositoryImpl;
import com.repository.impl.FacultyCourseRepositoryImpl;
import com.repository.impl.FacultyRepositoryImpl;
import com.service.FacultyCourseService;
import com.util.ValidationUtil;

public class FacultyCourseServiceImpl implements FacultyCourseService {

    private FacultyRepository facultyRepository;
    private CourseRepository courseRepository;
    private FacultyCourseRepository facultyCourseRepository;

    public FacultyCourseServiceImpl() {
        this.facultyRepository = new FacultyRepositoryImpl();
        this.courseRepository = new CourseRepositoryImpl();
        this.facultyCourseRepository = new FacultyCourseRepositoryImpl();
    }

    @Override
    public void assignFacultyToCourse(int facultyId, int courseId) {
    	

        Faculty faculty = facultyRepository.getFacultyById(facultyId);

        if (faculty == null) {
            System.out.println("Faculty not found.");
            return;
        }

        Course course = courseRepository.getCourseId(courseId);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        FacultyCourse facultyCourse = new FacultyCourse();

        facultyCourse.setFaculty(faculty);
        facultyCourse.setCourse(course);

        facultyCourseRepository.saveFacultyCourse(facultyCourse);

        System.out.println("Faculty assigned to course successfully.");
    }

    
    
    
    
    @Override
    public List<FacultyCourse> getAllAssignments() {

        return facultyCourseRepository.getAll();
    }

    @Override
    public void removeAssignment(int assignmentId) {

        FacultyCourse assignment =
                facultyCourseRepository.getById(assignmentId);

        if (assignment == null) {
            System.out.println("Assignment not found.");
            return;
        }

        facultyCourseRepository.deleteFacultyCourse(assignmentId);

    }
}