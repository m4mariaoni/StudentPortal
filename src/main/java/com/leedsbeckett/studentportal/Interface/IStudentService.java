package com.leedsbeckett.studentportal.Interface;

import com.leedsbeckett.studentportal.Entity.Student;
import com.leedsbeckett.studentportal.Models.StudentModel;


public interface StudentService {
    void saveStudent(StudentModel studentModel);

    Student findStudentByEmail(String email);
}
