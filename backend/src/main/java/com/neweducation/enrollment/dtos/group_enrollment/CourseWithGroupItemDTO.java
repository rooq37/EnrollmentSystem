package com.neweducation.enrollment.dtos.group_enrollment;

import com.neweducation.enrollment.dtos.enrollment_home.CourseItemDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseWithGroupItemDTO {

    private CourseItemDTO course;
    private GroupItemDTO group;

}
