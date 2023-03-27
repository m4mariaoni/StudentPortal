package com.leedsbeckett.studentportal.Service.Implementation;

import com.leedsbeckett.studentportal.Entity.Student;
import com.leedsbeckett.studentportal.Models.StudentModel;
import com.leedsbeckett.studentportal.Repository.StudentRepository;
import com.leedsbeckett.studentportal.Service.Interface.StudentService;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImplementation implements StudentService {
    private StudentRepository studentRepository;

    public StudentServiceImplementation(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    @Override
    public void saveStudent(StudentModel studentModel) {

    }

    @Override
    public Student findStudentByEmail(String email) {
        return null;
    }
}
