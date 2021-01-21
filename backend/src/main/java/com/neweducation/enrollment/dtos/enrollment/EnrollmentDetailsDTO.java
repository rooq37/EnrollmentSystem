package com.neweducation.enrollment.dtos.enrollment;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class EnrollmentDetailsDTO {

    private boolean isLimited;
    private Date startDate;
    private Date endDate;
    private boolean isCorrection;

}
