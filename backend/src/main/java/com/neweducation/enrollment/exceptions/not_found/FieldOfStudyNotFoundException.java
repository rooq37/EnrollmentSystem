package com.neweducation.enrollment.exceptions.not_found;

public class FieldOfStudyNotFoundException extends NotFoundException {

    public FieldOfStudyNotFoundException(String fieldOfStudyCode) {
        super("Kierunek studiów o kodzie " + fieldOfStudyCode + " nie został znaleziony!");
    }

}
