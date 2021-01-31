package com.neweducation.enrollment.controllers.group_enrollment;

import com.neweducation.enrollment.dtos.MessageDTO;
import com.neweducation.enrollment.dtos.group_enrollment.CourseListDTO;
import com.neweducation.enrollment.dtos.group_enrollment.CourseWithGroupItemDTO;
import com.neweducation.enrollment.dtos.group_enrollment.GroupItemDTO;
import com.neweducation.enrollment.dtos.group_enrollment.SubscriptionDTO;
import com.neweducation.enrollment.services.group_enrollment.GroupEnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/group-enrollment")
public class GroupEnrollmentController {

    private final GroupEnrollmentService groupEnrollmentService;

    public GroupEnrollmentController(GroupEnrollmentService groupEnrollmentService) {
        this.groupEnrollmentService = groupEnrollmentService;
    }

    @GetMapping("/courses")
    public ResponseEntity<CourseListDTO> getCourses(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "fieldOfStudyCode") String fieldOfStudyCode,
            @RequestParam(name = "enrollmentBlockId") Long enrollmentBlockId) {
        return ResponseEntity.ok(groupEnrollmentService.getCourseList(studentIndex, fieldOfStudyCode, enrollmentBlockId));
    }

    @GetMapping("/groups")
    public ResponseEntity<List<GroupItemDTO>> getGroups(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "courseCode") String courseCode,
            @RequestParam(name = "enrollmentBlockId") Long enrollmentBlockId) {
        return ResponseEntity.ok(groupEnrollmentService.getGroupList(studentIndex, courseCode, enrollmentBlockId));
    }

    @GetMapping("/current-courses-with-groups")
    public ResponseEntity<List<CourseWithGroupItemDTO>> getCurrentCoursesWithGroups(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "fieldOfStudyCode") String fieldOfStudyCode) {
        return ResponseEntity.ok(groupEnrollmentService.getCurrentCourses(studentIndex, fieldOfStudyCode));
    }

    @GetMapping("/overdue-courses-with-groups")
    public ResponseEntity<List<CourseWithGroupItemDTO>> getOverdueCoursesWithGroups(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "fieldOfStudyCode") String fieldOfStudyCode) {
        return ResponseEntity.ok(groupEnrollmentService.getOverdueCourses(studentIndex, fieldOfStudyCode));
    }

    @PostMapping("/subscription")
    public ResponseEntity<MessageDTO> subscribeToTheGroup(@RequestBody SubscriptionDTO subscriptionDTO) {
        return ResponseEntity.ok(groupEnrollmentService.subscribeToTheGroup(subscriptionDTO));
    }

    @DeleteMapping("/subscription")
    public ResponseEntity<MessageDTO> unsubscribeFromTheGroup(
            @RequestParam(name = "studentIndex") Long studentIndex,
            @RequestParam(name = "groupCode") String groupCode) {
        return ResponseEntity.ok(groupEnrollmentService.unsubscribeFromTheGroup(studentIndex, groupCode));
    }
}
