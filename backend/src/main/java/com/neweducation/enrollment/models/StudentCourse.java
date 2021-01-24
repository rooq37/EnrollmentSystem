package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "STUDENT_COURSES")
public class StudentCourse {

    @EmbeddedId
    private StudentCourseId studentCourseId = new StudentCourseId();

    @ManyToOne
    @MapsId("studentIndex")
    private Student student;

    @ManyToOne
    @MapsId("courseCode")
    private Course course;

    @Column(name = "ENROLLMENT_PERMISSION")
    private boolean enrollmentPermission;

    @Column(name = "COURSE_STATUS")
    private CourseStatus courseStatus;
}
