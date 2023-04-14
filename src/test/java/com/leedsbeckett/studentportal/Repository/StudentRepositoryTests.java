package com.leedsbeckett.studentportal.Repository;

import com.leedsbeckett.studentportal.Entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class StudentRepositoryTests {
    @Autowired
    private StudentRepository repo;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void TestCreateStudent(){
        Student student=new Student();
        student.setFirstName("Mimi");
        student.setLastName("Nana");
        student.setStudentId("C123456789");
        student.setEmail("mimi@yahoo.com");
        student.setPassword("Password");
        Student savedStudent = repo.save(student);

        Student existStudent = entityManager.find(Student.class,savedStudent.getId());

        assertThat(existStudent.getEmail()).isEqualTo(student.getEmail());

    }

    @Test
    public void testFindStudentEmail(){
        String email = "mimi@yahoo.com";
        Student student = repo.findByEmail(email);
        assertThat(student).isNotNull();
    }

    @Test
    public void TestListAll(){
        Iterable<Student> students = repo.findAll();
        assertThat(students).hasSizeGreaterThan(0);

        for(Student student : students){
            System.out.println(student);
        }
    }

    @Test
    public void TestUpdate(){
        Long id = 1L;
        Optional<Student> optionalStudent = repo.findById(id);
        Student student = optionalStudent.get();
        student.setLastName("Mynewlastname");
        repo.save(student);

        Student updatedStudent = repo.findById(student.id).get();
        assertThat(updatedStudent.getLastName()).isEqualTo("Mynewlastname");
    }

    @Test
    public void TestGet(){
        Long id = 1L;
        Optional<Student> optionalStudent= repo.findById(id);
        assertThat(optionalStudent.isPresent());
        System.out.println(optionalStudent.get());

    }
}


