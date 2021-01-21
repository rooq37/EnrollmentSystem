package com.neweducation.enrollment.controllers.enrollment;

import com.neweducation.enrollment.dtos.enrollment.EnrollmentBlocksDTO;
import com.neweducation.enrollment.services.enrollment.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/blocks/{studentIndex}")
    public ResponseEntity<EnrollmentBlocksDTO> getEnrollmentBlocks(@PathVariable String studentIndex) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentBlocks(studentIndex));
    }
}
