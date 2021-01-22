package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity(name = "STUDYING_DETAILS")
public class StudyingDetails {

    @EmbeddedId
    private StudyingDetailsId id = new StudyingDetailsId();

    @ManyToOne
    @MapsId("studentIndex")
    private Student student;

    @ManyToOne
    @MapsId("fieldOfStudyCode")
    private FieldOfStudy fieldOfStudy;

    private int semester;

    @Column(name = "SEMESTER_TYPE")
    private String semesterType;

    @Column(name = "START_DATE")
    private Date startDate;

}
