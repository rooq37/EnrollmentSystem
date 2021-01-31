package com.neweducation.enrollment.exceptions.not_found;

public abstract class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

}
