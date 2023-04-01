package com.leedsbeckett.studentportal.Service;

import com.leedsbeckett.studentportal.Entity.Course;
import com.leedsbeckett.studentportal.Entity.Student;
import com.leedsbeckett.studentportal.Interface.IStudentService;
import com.leedsbeckett.studentportal.Models.AccountModel;
import com.leedsbeckett.studentportal.Models.CourseModel;
import com.leedsbeckett.studentportal.Models.StudentModel;
import com.leedsbeckett.studentportal.Repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService implements IStudentService {
    private StudentRepository studentRepository;
    private PasswordEncoder passwordEncoder;
    private IntegrationService integrationService;


    public StudentService(StudentRepository studentRepository,
    PasswordEncoder passwordEncoder, IntegrationService integrationService){
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
        this.integrationService = integrationService;
    }

    @Override
    public void saveStudent(StudentModel studentmodel) {
        Student student = new Student();
        student.setPassword(passwordEncoder.encode(studentmodel.getPassword()));
        student.setEmail(studentmodel.getEmail());
        student.setFirstName(studentmodel.getFirstName());
        student.setLastName(studentmodel.getLastName());
        student.setStudentId(studentmodel.getStudentId());
        studentRepository.save(student);

        AccountModel accountModel = new AccountModel();
        accountModel.studentId = student.getStudentId();
        integrationService.createFinanceAccount(accountModel);

        integrationService.createLibraryAccount(student);

    }

    @Override
    public Student findStudentByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Override
    public List<StudentModel> findAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map((student) -> mapToStudentDto(student))
                .collect(Collectors.toList());
    }

    @Override
    public Student getStudentById(Long Id) {
        return studentRepository.getById(Id);
    }

    private StudentModel mapToStudentDto(Student student){
        StudentModel studentModel = new StudentModel();
        studentModel.setFirstName(student.getFirstName());
        studentModel.setLastName(student.getLastName());
        studentModel.setEmail(student.getEmail());
        return studentModel;
    }

    @Override
    public void updateStudent(StudentModel studentmodel) {
        ModelMapper modelMapper = new ModelMapper();
        Student student = new Student();
        //student.setPassword(passwordEncoder.encode(studentmodel.getPassword()));
        modelMapper.map(studentmodel, student);
        studentRepository.save(student);
    }



}
