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
public class EnrollmentId implements Serializable {

    private Long studentIndex;
    private String groupCode;

    public EnrollmentId(Long studentIndex, String groupCode) {
        super();
        this.studentIndex = studentIndex;
        this.groupCode = groupCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(studentIndex, that.studentIndex) &&
                Objects.equals(groupCode, that.groupCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentIndex, groupCode);
    }
}
