package com.neweducation.enrollment.dtos.group_enrollment;

import com.neweducation.enrollment.dtos.enrollment_home.CourseItemDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseListDTO {

    private Long enrollmentBlockId;
    private String enrollmentBlockName;
    private String fieldOfStudyCode;
    private String fieldOfStudyName;
    private List<CourseItemDTO> courses;

}
