package com.leedsbeckett.studentportal.Repository;

import com.leedsbeckett.studentportal.Entity.Course;
import com.leedsbeckett.studentportal.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
