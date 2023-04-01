package com.leedsbeckett.studentportal.Exceptions;

public class CourseNotFoundException extends Throwable {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
