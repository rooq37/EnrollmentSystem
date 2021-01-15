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

    private boolean isLimited;

    private Date startDate;

    private Date endDate;

    private boolean isCorrection;

    @ManyToMany(mappedBy = "enrollmentBlocks")
    private Set<Student> students = new HashSet<>();

}
