package com.neweducation.enrollment.models;

public enum EnrollmentMode {

    NORMAL("Normalny"),
    CORRECTION("Korekty"),
    ADMINISTRATION("Administracyjny");

    private final String value;

    EnrollmentMode(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
