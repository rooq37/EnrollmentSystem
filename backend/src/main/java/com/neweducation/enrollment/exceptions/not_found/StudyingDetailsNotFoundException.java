package com.neweducation.enrollment.exceptions.not_found;

public class StudyingDetailsNotFoundException extends RuntimeException {

    public StudyingDetailsNotFoundException(Long studentIndex, String fieldOfStudyCode) {
        super("Student o numerze indeksu " + studentIndex + " nie studiuje kierunku o kodzie " + fieldOfStudyCode + "!");
    }

}
