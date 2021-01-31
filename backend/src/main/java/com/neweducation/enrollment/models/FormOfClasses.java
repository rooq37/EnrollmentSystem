package com.neweducation.enrollment.models;

public enum FormOfClasses {

    LABORATORY("Laboratorium"),
    LECTURE("Wykład"),
    EXERCISES("Ćwiczenia"),
    PROJECT("Projekt"),
    SEMINAR("Seminarium");

    private final String value;

    FormOfClasses(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
