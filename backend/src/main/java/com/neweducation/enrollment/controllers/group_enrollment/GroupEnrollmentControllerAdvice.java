package com.neweducation.enrollment.controllers.group_enrollment;

import com.neweducation.enrollment.dtos.MessageDTO;
import com.neweducation.enrollment.exceptions.enrollment.EnrollmentException;
import com.neweducation.enrollment.exceptions.not_found.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GroupEnrollmentControllerAdvice {

    @ResponseBody
    @ExceptionHandler(EnrollmentException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    MessageDTO enrollmentExceptionHandler(EnrollmentException ex) {
        return new MessageDTO(ex.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    MessageDTO notFoundExceptionHandler(NotFoundException ex) {
        return new MessageDTO(ex.getMessage());
    }

}
