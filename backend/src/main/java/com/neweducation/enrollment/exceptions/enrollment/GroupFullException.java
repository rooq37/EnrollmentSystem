package com.neweducation.enrollment.exceptions.enrollment;

public class GroupFullException extends EnrollmentException {

    public GroupFullException(String groupCode, String courseCode, String courseName) {
        super("Grupa " + groupCode + " należąca do kursu " + courseName + " (" + courseCode + ") jest już pełna!");
    }

}
