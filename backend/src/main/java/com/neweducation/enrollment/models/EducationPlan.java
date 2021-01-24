package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "EDUCATION_PLANS")
public class EducationPlan {

    @Id
    private Long id;

    @Column(name = "VALID_FROM")
    private Date validFrom;

    @ManyToOne
    @MapsId("educationPlans")
    private FieldOfStudy fieldOfStudy;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "EDUCATION_PLAN_COURSES",
            joinColumns = @JoinColumn(name = "EDUCATION_PLAN_ID"),
            inverseJoinColumns = @JoinColumn(name = "COURSE_CODE"))
    private Set<Course> courses = new HashSet<>();

}
