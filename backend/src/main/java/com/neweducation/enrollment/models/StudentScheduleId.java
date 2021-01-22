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
public class StudentScheduleId implements Serializable {

    private Long studentIndex;
    private Long enrollmentBlockId;

    public StudentScheduleId(Long studentIndex, Long enrollmentBlockId) {
        super();
        this.studentIndex = studentIndex;
        this.enrollmentBlockId = enrollmentBlockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentScheduleId that = (StudentScheduleId) o;
        return Objects.equals(studentIndex, that.studentIndex) &&
                Objects.equals(enrollmentBlockId, that.enrollmentBlockId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentIndex, enrollmentBlockId);
    }
}
