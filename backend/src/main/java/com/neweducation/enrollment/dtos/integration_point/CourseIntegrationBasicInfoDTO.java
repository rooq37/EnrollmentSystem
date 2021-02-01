package com.neweducation.enrollment.dtos.integration_point;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseIntegrationBasicInfoDTO {
    private static final String NOTHING_CHANGED = "No courses were added";
    private static final String COURSES_ADDED = "Courses were added";

    private String message;
    private int changedCount;

    CourseIntegrationBasicInfoDTO() {
        this.message = NOTHING_CHANGED;
        this.changedCount = 0;
    }

    CourseIntegrationBasicInfoDTO(int changedCount) {
        this.message = COURSES_ADDED;
        this.changedCount = changedCount;
    }
}
