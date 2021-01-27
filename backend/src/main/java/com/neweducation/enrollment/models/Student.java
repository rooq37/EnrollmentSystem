package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "STUDENTS")
public class Student {

    @Id
    private Long index;

    @OneToMany(mappedBy = "student")
    private Set<StudentSchedule> studentSchedules = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<StudyingDetails> studyingDetails = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<StudentCourse> studentCourses = new HashSet<>();
}
