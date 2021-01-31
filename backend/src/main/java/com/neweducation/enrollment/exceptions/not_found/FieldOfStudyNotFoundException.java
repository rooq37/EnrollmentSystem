package com.neweducation.enrollment.exceptions.not_found;

public class FieldOfStudyNotFoundException extends RuntimeException {

    public FieldOfStudyNotFoundException(String fieldOfStudyCode) {
        super("Kierunek studiów o kodzie " + fieldOfStudyCode + " nie został znaleziony!");
    }

}
