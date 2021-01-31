package com.neweducation.enrollment.dtos.enrollment_home;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EnrollmentDetailsDTO {

    @JsonProperty("isLimited")
    private boolean enrollmentBlockIsLimited;
    @JsonProperty("studentStartDate")
    private Date startDate;
    @JsonProperty("studentEndDate")
    private Date endDate;
    @JsonProperty("isCorrection")
    private boolean enrollmentBlockIsCorrection;
    @JsonProperty("startDate")
    private Date enrollmentBlockStartDate;
    @JsonProperty("endDate")
    private Date enrollmentBlockEndDate;

}
