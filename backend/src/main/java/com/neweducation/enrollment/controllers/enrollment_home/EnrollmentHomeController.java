package com.neweducation.enrollment.controllers.enrollment_home;

import com.neweducation.enrollment.dtos.enrollment_home.CourseItemDTO;
import com.neweducation.enrollment.dtos.enrollment_home.EnrollmentBlockDTO;
import com.neweducation.enrollment.dtos.enrollment_home.EnrollmentDetailsDTO;
import com.neweducation.enrollment.dtos.enrollment_home.FieldOfStudyDTO;
import com.neweducation.enrollment.services.enrollment_home.EnrollmentHomeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentHomeController {

    private final EnrollmentHomeService enrollmentHomeService;

    public EnrollmentHomeController(EnrollmentHomeService enrollmentHomeService) {
        this.enrollmentHomeService = enrollmentHomeService;
    }

    @GetMapping("/fields/{studentIndex}")
    public ResponseEntity<List<FieldOfStudyDTO>> getFieldsOfStudy(@PathVariable Long studentIndex) {
        return ResponseEntity.ok(enrollmentHomeService.getFieldsOfStudy(studentIndex));
    }

    @GetMapping("/blocks")
    public ResponseEntity<List<EnrollmentBlockDTO>> getEnrollmentBlocks(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "fieldOfStudyCode") String fieldOfStudyCode) {
        return ResponseEntity.ok(enrollmentHomeService.getEnrollmentBlocks(studentIndex, fieldOfStudyCode));
    }

    @GetMapping("/details")
    public ResponseEntity<EnrollmentDetailsDTO> getEnrollmentDetails(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "enrollmentBlockId") Long enrollmentBlockId) {
        return ResponseEntity.ok(enrollmentHomeService.getEnrollmentDetails(studentIndex, enrollmentBlockId));
    }

    @GetMapping("/current-courses")
    public ResponseEntity<List<CourseItemDTO>> getCurrentCourses(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "fieldOfStudyCode") String fieldOfStudyCode) {
        return ResponseEntity.ok(enrollmentHomeService.getCurrentCourses(studentIndex, fieldOfStudyCode));
    }

    @GetMapping("/overdue-courses")
    public ResponseEntity<List<CourseItemDTO>> getOverdueCourses(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "fieldOfStudyCode") String fieldOfStudyCode) {
        return ResponseEntity.ok(enrollmentHomeService.getOverdueCourses(studentIndex, fieldOfStudyCode));
    }
}
