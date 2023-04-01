package com.leedsbeckett.studentportal.Service;

import com.leedsbeckett.studentportal.Entity.Course;
import com.leedsbeckett.studentportal.Entity.Student;
import com.leedsbeckett.studentportal.Exceptions.CourseNotFoundException;
import com.leedsbeckett.studentportal.Interface.ICourseService;
import com.leedsbeckett.studentportal.Models.CourseModel;
import com.leedsbeckett.studentportal.Models.StudentModel;
import com.leedsbeckett.studentportal.Repository.CourseRepository;
import com.leedsbeckett.studentportal.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService implements ICourseService {
    private CourseRepository courseRepository;
    private StudentRepository studentRepository;

    public CourseService(CourseRepository courseRepository,StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public List<CourseModel> findAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map((course) -> mapToCourseDto(course))
                .collect(Collectors.toList());
    }


    private CourseModel mapToCourseDto(Course course){
        CourseModel courseModel = new CourseModel();
        courseModel.setId((course.getId()));
        courseModel.setCode(course.getCode());
        courseModel.setName(course.getName());
        courseModel.setDescription(course.getDescription());
        courseModel.setFee(course.getFee());
        return courseModel;
    }

    public CourseModel getbyId(Long id) throws CourseNotFoundException {
        Optional<Course> result = courseRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        if(result.isPresent()){
            return modelMapper.map(result.get(), CourseModel.class);
        }
        throw new CourseNotFoundException("Course not found with id " + id);
    }

    public Course getCoursebyId(Long id) throws CourseNotFoundException {
        Optional<Course> result = courseRepository.findById(id);
        ModelMapper modelMapper = new ModelMapper();
        if(result.isPresent()){
            return modelMapper.map(result.get(), Course.class);
        }
        throw new CourseNotFoundException("Course not found with id " + id);
    }

    public Course enrollStudentToCourse(long studentId, long courseId){
        Course course = courseRepository.findById(courseId).get();
        Student student = studentRepository.findById(studentId).get();
        course.enrolledStudents(student);
        courseRepository.save(course);
        return course;
    }
}
