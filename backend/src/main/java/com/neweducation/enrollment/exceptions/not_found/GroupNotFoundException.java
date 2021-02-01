package com.neweducation.enrollment.exceptions.not_found;

public class GroupNotFoundException extends NotFoundException {

    public GroupNotFoundException(String groupCode) {
        super("Grupa o kodzie " + groupCode + " nie została znaleziona!");
    }

}
