package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import java.util.Date;

@Getter
@Setter
@Entity(name = "STUDENT_SCHEDULES")
public class StudentSchedule {

    @EmbeddedId
    private StudentScheduleId id = new StudentScheduleId();

    @ManyToOne
    @MapsId("studentIndex")
    private Student student;

    @ManyToOne
    @MapsId("enrollmentBlockId")
    private EnrollmentBlock enrollmentBlock;

    private Date startDate;

    private Date endDate;

}
