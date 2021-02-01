package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "COURSES")
@ToString
public class Course {

    @Id
    private String code;
    private String name;

    @Column(name = "PLANNED_SEMESTER_OF_REALIZATION")
    private int plannedSemesterOfRealization;

    @Column(name = "SEMESTER_TYPE")
    private SemesterType semesterType;

    @Column(name = "FORM_OF_CLASSES")
    private FormOfClasses formOfClasses;

    @Column(name = "IS_UNIVERSITY_WIDE")
    private boolean isUniversityWide;

    @Column(name = "IS_SELECTABLE")
    private boolean isSelectable;

    @Column(name = "NAME_OF_SELECTABLE_MODULE")
    private String nameOfSelectableModule;

    @Column(name = "NUMBER_OF_ECTS")
    private int numberOfEcts;

    @ManyToMany(mappedBy = "courses")
    private Set<EducationPlan> educationPlans = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<StudentCourse> studentCourses = new HashSet<>();

    @OneToMany(mappedBy = "course")
    private Set<Group> groups = new HashSet<>();
}
