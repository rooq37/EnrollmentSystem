package com.neweducation.enrollment.exceptions.not_found;

public class EnrollmentBlockNotFoundException extends NotFoundException {

    public EnrollmentBlockNotFoundException(Long enrollmentBlockId) {
        super("Blok zapisów o numerze id " + enrollmentBlockId + " nie został znaleziony!");
    }

}
