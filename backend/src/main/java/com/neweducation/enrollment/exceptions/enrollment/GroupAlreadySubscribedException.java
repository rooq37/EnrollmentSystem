package com.neweducation.enrollment.exceptions.enrollment;

public class GroupAlreadySubscribedException extends EnrollmentException {
    public GroupAlreadySubscribedException(String groupCode) {
        super("Jesteś już zapisany do grupy " + groupCode + "!");
    }
}
