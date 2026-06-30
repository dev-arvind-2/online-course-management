package com.service;


import java.util.List;

import com.entity.Student;

public interface StudentService {

    void addStudent(Student student);
    
    void updateStudent(Student student);

    void deleteStudent(int id);

    Student getStudentById(int id);

    List<Student> getAllStudents();

   
    


}