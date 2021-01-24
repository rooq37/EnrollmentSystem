package com.neweducation.enrollment.controllers.enrollment;

import com.neweducation.enrollment.dtos.enrollment.CourseItemDTO;
import com.neweducation.enrollment.dtos.enrollment.EnrollmentBlockDTO;
import com.neweducation.enrollment.dtos.enrollment.EnrollmentDetailsDTO;
import com.neweducation.enrollment.dtos.enrollment.FieldOfStudyDTO;
import com.neweducation.enrollment.services.enrollment.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/fields/{studentIndex}")
    public ResponseEntity<List<FieldOfStudyDTO>> getFieldsOfStudy(@PathVariable Long studentIndex) {
        return ResponseEntity.ok(enrollmentService.getFieldsOfStudy(studentIndex));
    }

    @GetMapping("/blocks")
    public ResponseEntity<List<EnrollmentBlockDTO>> getEnrollmentBlocks(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "fieldOfStudyCode") String fieldOfStudyCode) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentBlocks(studentIndex, fieldOfStudyCode));
    }

    @GetMapping("/details")
    public ResponseEntity<EnrollmentDetailsDTO> getEnrollmentDetails(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "enrollmentBlockId") Long enrollmentBlockId) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentDetails(studentIndex, enrollmentBlockId));
    }

    @GetMapping("/current-courses")
    public ResponseEntity<List<CourseItemDTO>> getCurrentCourses(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "fieldOfStudyCode") String fieldOfStudyCode) {
        return ResponseEntity.ok(enrollmentService.getCurrentCourses(studentIndex, fieldOfStudyCode));
    }

    @GetMapping("/overdue-courses")
    public ResponseEntity<List<CourseItemDTO>> getOverdueCourses(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "fieldOfStudyCode") String fieldOfStudyCode) {
        return ResponseEntity.ok(enrollmentService.getOverdueCourses(studentIndex, fieldOfStudyCode));
    }
}
