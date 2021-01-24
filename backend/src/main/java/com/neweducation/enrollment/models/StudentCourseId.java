package com.neweducation.enrollment.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class StudentCourseId implements Serializable {

    private Long studentIndex;
    private String courseCode;

    public StudentCourseId(Long studentIndex, String courseCode) {
        this.studentIndex = studentIndex;
        this.courseCode = courseCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseId that = (StudentCourseId) o;
        return Objects.equals(studentIndex, that.studentIndex) &&
                Objects.equals(courseCode, that.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentIndex, courseCode);
    }
}
