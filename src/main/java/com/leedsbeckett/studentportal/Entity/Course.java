package com.leedsbeckett.studentportal.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(nullable = false)
    public String name;
    @Column(nullable = false, length = 10)
    public String code;
    @Column(nullable = false)
    public String description;
    @Column(nullable = true)
    public double fee;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }


    public Set<Student> getEnrolledCourses() {
        return students;
    }
    @JsonIgnore
    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<Student> students = new HashSet<>();

}
