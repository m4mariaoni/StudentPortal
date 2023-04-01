package com.leedsbeckett.studentportal.Interface;

import com.leedsbeckett.studentportal.Entity.Course;
import com.leedsbeckett.studentportal.Entity.Student;
import com.leedsbeckett.studentportal.Exceptions.CourseNotFoundException;
import com.leedsbeckett.studentportal.Models.CourseModel;
import com.leedsbeckett.studentportal.Models.StudentModel;

import java.util.List;

public interface ICourseService {
    List<CourseModel> findAllCourses();

    CourseModel getbyId(Long id) throws CourseNotFoundException;
    Course getCoursebyId(Long id) throws CourseNotFoundException;
    Course enrollStudentToCourse(long subjectId, long courseId);
}
