package com.neweducation.enrollment.exceptions;

public class EnrollmentBlockNotFoundException extends RuntimeException {

    public EnrollmentBlockNotFoundException(Long enrollmentBlockId) {
        super("Blok zapisów o numerze id " + enrollmentBlockId + " nie został znaleziony!");
    }

}
