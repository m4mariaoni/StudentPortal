package com.leedsbeckett.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public  long id;


    @ManyToMany
    @JoinTable(
            name="student_enrolled",
            joinColumns = @JoinColumn(name="student_id"),
            inverseJoinColumns = @JoinColumn(name="course_id")
    )

    private Set<Course> enrolledCourses = new HashSet<>();

    public void enrolledCourses(Course course) {
        enrolledCourses.add(course);
    }

    public Set<Course> getEnrolledCourses() {
        return enrolledCourses;
    }


    @Column(nullable = false)
    public  String studentId;
    @Column(nullable = false, length = 20)
    public  String firstName;
    @Column(nullable = false, length = 20)
    public  String lastName;
    @Column(name= "email", nullable = false,unique = true, length = 45)
    public  String email;
    @Column(nullable = false, length = 64)
    public String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
