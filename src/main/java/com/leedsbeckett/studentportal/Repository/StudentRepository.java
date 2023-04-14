package com.leedsbeckett.studentportal.Repository;

import com.leedsbeckett.studentportal.Entity.Course;
import com.leedsbeckett.studentportal.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByEmail(String email);
     @Query("select t from Course t left join fetch t.students p where p.studentId= :userId")
            List<Course> findAllCoursesByUserId(String userId);
}
