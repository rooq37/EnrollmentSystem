package com.neweducation.enrollment.services.integration_point;

import com.neweducation.enrollment.models.Course;
import com.neweducation.enrollment.repositories.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class CourseIntegrationService {

    private final String uri;

    private final RestTemplate restTemplate;
    private final CourseRepository courseRepository;

    public CourseIntegrationService(
        @Value("${integration.point.course.host}") String host,
        @Value("${integration.point.course.port}") String port,
        @Value("${integration.point.course.filename}") String filename,
        RestTemplate restTemplate,
        CourseRepository courseRepository
    ) throws URISyntaxException {
        this.uri = host + ':' + port + '/' + filename;
        this.restTemplate = restTemplate;
        this.courseRepository = courseRepository;
    }

    public Course[] retrieveCourses() {
        Course[] latestCourses = this.restTemplate.getForObject(uri, Course[].class);

        if (latestCourses == null) {
            return new Course[0];
        }

        if (latestCourses.length == 0) {
            return latestCourses;
        }

        List<Course> coursesAdded = new ArrayList<>(Arrays.asList(latestCourses));

        for (Course course : latestCourses) {
            if (!this.courseRepository.existsById(course.getCode())) {
                this.courseRepository.save(course);
                log.info("Course" + course.getCode() + " " + course.getName() + " was saved: " + course);
            } else {
                log.info("Course" + course.getCode() + " " + course.getName() + " exists: " + course);
                coursesAdded.remove(course);
            }
        }

        return coursesAdded.toArray(new Course[0]);
    }
}
