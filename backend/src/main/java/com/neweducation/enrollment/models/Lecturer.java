package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity(name = "LECTURERS")
public class Lecturer {

    @Id
    private Long id;

    private String title;
    private String name;
    private String surname;

    @ManyToMany(mappedBy = "lecturers")
    private Set<Group> groups = new HashSet<>();

}
