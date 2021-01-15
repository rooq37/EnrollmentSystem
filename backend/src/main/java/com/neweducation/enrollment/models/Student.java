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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "STUDENTS_ENROLLMENT_BLOCKS",
            joinColumns = @JoinColumn(name = "STUDENT_INDEX"),
            inverseJoinColumns = @JoinColumn(name = "ENROLLMENT_BLOCK_ID"))
    private Set<EnrollmentBlock> enrollmentBlocks = new HashSet<>();

}
