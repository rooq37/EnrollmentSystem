package com.neweducation.enrollment.exceptions.not_found;

public class StudentNotFoundException extends NotFoundException {

    public StudentNotFoundException(String studentIndex) {
        super("Student o numerze indeksu " + studentIndex + " nie został znaleziony!");
    }

}
