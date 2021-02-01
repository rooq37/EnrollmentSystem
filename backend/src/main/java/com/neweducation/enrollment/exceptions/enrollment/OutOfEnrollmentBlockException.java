package com.neweducation.enrollment.exceptions.enrollment;

public class OutOfEnrollmentBlockException extends EnrollmentException {

    public OutOfEnrollmentBlockException(String enrollmentBlockName) {
        super("Blok zapisów \"" + enrollmentBlockName + "\" jeszcze się nie rozpoczął lub już się skończył!");
    }

}
