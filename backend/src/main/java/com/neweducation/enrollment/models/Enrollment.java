package com.neweducation.enrollment.models;

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
}
