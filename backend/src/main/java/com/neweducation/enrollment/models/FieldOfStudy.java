package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "FIELDS_OF_STUDY")
public class FieldOfStudy {

    @Id
    private String code;
    private String name;

    @OneToMany(mappedBy = "fieldOfStudy")
    private Set<StudyingDetails> studyingDetails = new HashSet<>();

    @ManyToMany(mappedBy = "fieldsOfStudy")
    private Set<EnrollmentBlock> enrollmentBlocks = new HashSet<>();
}
