package com.neweducation.enrollment.dtos.enrollment;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseItemDTO {

    private String name;
    private String code;
    private String formOfClasses;
    private int numberOfEcts;
    @JsonProperty("isSelectable")
    private boolean isSelectable;

}
