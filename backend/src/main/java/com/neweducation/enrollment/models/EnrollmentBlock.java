package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "ENROLLMENT_BLOCKS")
public class EnrollmentBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String realization;

    @Column(name = "IS_LIMITED")
    private boolean isLimited;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "IS_CORRECTION")
    private boolean isCorrection;

    @OneToMany(mappedBy = "enrollmentBlock")
    private Set<StudentSchedule> studentSchedules = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "ENROLLMENT_BLOCK_STUDYING_DETAILS",
            joinColumns = @JoinColumn(name = "ENROLLMENT_BLOCK_ID"),
            inverseJoinColumns = @JoinColumn(name = "FIELD_OF_STUDY_CODE"))
    private Set<FieldOfStudy> fieldsOfStudy = new HashSet<>();
}
