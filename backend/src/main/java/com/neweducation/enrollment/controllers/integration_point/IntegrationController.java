package com.neweducation.enrollment.controllers.integration_point;

import com.neweducation.enrollment.dtos.enrollment_home.CourseItemDTO;
import com.neweducation.enrollment.dtos.integration_point.CourseIntegrationDTO;
import com.neweducation.enrollment.models.Course;
import com.neweducation.enrollment.services.EnrollmentMapper;
import com.neweducation.enrollment.services.integration_point.CourseIntegrationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/integration")
public class IntegrationController {

    private final CourseIntegrationService courseService;
    private final EnrollmentMapper enrollmentMapper;

    public IntegrationController(CourseIntegrationService courseService) {
        this.courseService = courseService;
        this.enrollmentMapper = new EnrollmentMapper();
    }

    @GetMapping(value = "/courses")
    public CourseIntegrationDTO integrateCourses() {
        Course[] courses = this.courseService.retrieveCourses();

        if (courses == null || courses.length == 0) {
            return new CourseIntegrationDTO();
        }

        return new CourseIntegrationDTO(
            Stream.of(courses)
                .map(enrollmentMapper::mapToCourseItem)
                .toArray(CourseItemDTO[]::new)
        );
    }
}
