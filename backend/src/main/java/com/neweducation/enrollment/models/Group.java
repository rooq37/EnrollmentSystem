package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "GROUPS")
public class Group {

    @Id
    private String code;

    private String realization;

    @Column(name = "DAY_OF_WEEK")
    private int dayOfWeek;

    private int hour;

    private int minute;

    private int duration;

    private Parity parity;

    @Column(name = "LIMIT_OF_PLACES")
    private int limitOfPlaces;

    private String place;

    @ManyToOne
    private Course course;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "GROUP_LECTURERS",
            joinColumns = @JoinColumn(name = "GROUP_CODE"),
            inverseJoinColumns = @JoinColumn(name = "LECTURER_ID"))
    private Set<Lecturer> lecturers = new HashSet<>();

    @OneToMany(mappedBy = "group")
    private Set<Enrollment> enrollments = new HashSet<>();
}
