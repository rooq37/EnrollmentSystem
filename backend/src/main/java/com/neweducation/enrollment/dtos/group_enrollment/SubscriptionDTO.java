package com.neweducation.enrollment.dtos.group_enrollment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SubscriptionDTO {

    private Long studentIndex;
    private String groupCode;
    private Long enrollmentBlockId;

}
