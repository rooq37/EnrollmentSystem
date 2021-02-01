package com.neweducation.enrollment.dtos.integration_point;

import com.neweducation.enrollment.dtos.enrollment_home.CourseItemDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseIntegrationDTO {

    private CourseIntegrationBasicInfoDTO basicInfo;
    private CourseItemDTO[] courses;

    public CourseIntegrationDTO() {
        this.basicInfo = new CourseIntegrationBasicInfoDTO();
        this.courses = new CourseItemDTO[0];
    }

    public CourseIntegrationDTO(CourseItemDTO[] courses) {
        this.basicInfo = new CourseIntegrationBasicInfoDTO(courses.length);
        this.courses = courses;
    }
}
