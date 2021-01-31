package com.neweducation.enrollment.exceptions.not_found;

public class StudentScheduleNotFoundException extends NotFoundException {

    public StudentScheduleNotFoundException(Long studentIndex, Long enrollmentBlockId) {
        super("Student o numerze indeksu " + studentIndex + " nie ma praw do bloku zapisowaego o identyfikatorze " + enrollmentBlockId + "!");
    }

}
