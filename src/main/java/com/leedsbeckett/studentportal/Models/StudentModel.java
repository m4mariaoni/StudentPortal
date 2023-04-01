package com.leedsbeckett.studentportal.Models;

import jakarta.persistence.Column;

public class StudentModel {

    public  long id;
    @Column(nullable = false, length = 10)
    public  String studentId;
    @Column(nullable = false, length = 20)
    public  String firstName;
    @Column(nullable = false, length = 20)
    public  String lastName;
    @Column(nullable = false,unique = true, length = 45)
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
