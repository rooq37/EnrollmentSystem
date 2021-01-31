package com.neweducation.enrollment.exceptions.not_found;

public class EnrollmentNotFoundException extends RuntimeException {

    public EnrollmentNotFoundException(Long studentIndex, String groupCode) {
        super("Student o numerze indeksu " + studentIndex + " nie jest zapisany do grupy " + groupCode + "!");
    }

}
