package com.neweducation.enrollment.models;

import com.neweducation.enrollment.exceptions.enrollment.GroupFullException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "ENROLLMENTS")
public class Enrollment {

    @EmbeddedId
    private EnrollmentId id = new EnrollmentId();

    @ManyToOne
    @MapsId("studentIndex")
    private Student student;

    @ManyToOne
    @MapsId("groupCode")
    private Group group;

    @Column(name = "ENROLLMENT_DATE")
    private Date enrollmentDate;

    @Column(name = "ENROLLMENT_MODE")
    private EnrollmentMode enrollmentMode;

    @PrePersist
    public void onPrePersist() {
        if(group.getLimitOfPlaces() == group.getEnrollments().size())
            throw new GroupFullException(group.getCode(), group.getCourse().getCode(), group.getCourse().getName());
    }
}
