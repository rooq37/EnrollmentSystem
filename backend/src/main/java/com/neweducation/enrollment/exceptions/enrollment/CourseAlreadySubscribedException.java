package com.neweducation.enrollment.exceptions.enrollment;

public class CourseAlreadySubscribedException extends EnrollmentException {

    public CourseAlreadySubscribedException(String courseCode, String courseName, String formOfClasses) {
        super("Jesteś już zapisany na kurs " + courseName + " - " + formOfClasses + " (" + courseCode + ")!");
    }
}
