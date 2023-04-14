package com.leedsbeckett.studentportal.Interface;

import com.leedsbeckett.studentportal.Entity.Course;
import com.leedsbeckett.studentportal.Entity.Student;
import com.leedsbeckett.studentportal.Models.StudentModel;

import java.util.List;


public interface IStudentService {
    void saveStudent(StudentModel studentModel);
    void updateStudent(StudentModel studentModel);
    Student findStudentByEmail(String email);
    List<StudentModel> findAllStudents();

    Student getStudentById(Long Id);
    Student enrollStudentToCourse(long studentId, long courseId);
    List<Course> findCoursesByStudentId(String studentId);

}
