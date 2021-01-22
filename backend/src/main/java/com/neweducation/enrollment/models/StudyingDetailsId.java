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
public class StudyingDetailsId implements Serializable {

    private Long studentIndex;
    private String fieldOfStudyCode;

    public StudyingDetailsId(Long studentIndex, String fieldOfStudyCode) {
        this.studentIndex = studentIndex;
        this.fieldOfStudyCode = fieldOfStudyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyingDetailsId that = (StudyingDetailsId) o;
        return Objects.equals(studentIndex, that.studentIndex) &&
                Objects.equals(fieldOfStudyCode, that.fieldOfStudyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentIndex, fieldOfStudyCode);
    }
}
