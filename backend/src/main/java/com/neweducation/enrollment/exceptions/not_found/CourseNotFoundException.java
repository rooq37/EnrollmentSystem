package com.neweducation.enrollment.exceptions.not_found;

public class CourseNotFoundException extends RuntimeException {

    public CourseNotFoundException(String courseCode) {
        super("Kurs o kodzie " + courseCode + " nie został znaleziony!");
    }

}
