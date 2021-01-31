package com.neweducation.enrollment.services;

import com.neweducation.enrollment.exceptions.not_found.FieldOfStudyNotFoundException;
import com.neweducation.enrollment.exceptions.not_found.StudentNotFoundException;
import com.neweducation.enrollment.exceptions.not_found.StudyingDetailsNotFoundException;
import com.neweducation.enrollment.models.*;
import com.neweducation.enrollment.repositories.FieldOfStudyRepository;
import com.neweducation.enrollment.repositories.StudentRepository;
import com.neweducation.enrollment.repositories.StudyingDetailsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    protected final StudyingDetailsRepository studyingDetailsRepository;
    protected final StudentRepository studentRepository;
    protected final FieldOfStudyRepository fieldOfStudyRepository;

    public EnrollmentService(StudyingDetailsRepository studyingDetailsRepository,
                             StudentRepository studentRepository,
                             FieldOfStudyRepository fieldOfStudyRepository) {

        this.studyingDetailsRepository = studyingDetailsRepository;
        this.studentRepository = studentRepository;
        this.fieldOfStudyRepository = fieldOfStudyRepository;
    }

    protected List<Course> getCourses(Long studentIndex, String fieldOfStudyCode, CourseStatus courseStatus) {
        Student student = studentRepository.findByIndex(studentIndex)
                .orElseThrow(() -> new StudentNotFoundException(String.valueOf(studentIndex)));

        FieldOfStudy fieldOfStudy = fieldOfStudyRepository.findByCode(fieldOfStudyCode)
                .orElseThrow(() -> new FieldOfStudyNotFoundException(fieldOfStudyCode));

        List<String> courseCodesFromEducationPlan = getEducationPlan(student, fieldOfStudy)
                .getCourses().stream().map(Course::getCode)
                .collect(Collectors.toList());

        return student.getStudentCourses().stream()
                .filter(studentCourse -> studentCourse.getCourseStatus().equals(courseStatus))
                .filter(StudentCourse::isEnrollmentPermission)
                .map(StudentCourse::getCourse)
                .filter(course -> courseCodesFromEducationPlan.contains(course.getCode()))
                .collect(Collectors.toList());
    }

    protected EducationPlan getEducationPlan(Student student, FieldOfStudy fieldOfStudy) {
        StudyingDetails studyingDetails = studyingDetailsRepository.findStudyingDetailsByStudentAndFieldOfStudy(student, fieldOfStudy)
                .orElseThrow(() -> new StudyingDetailsNotFoundException(student.getIndex(), fieldOfStudy.getCode()));

        return fieldOfStudy.getEducationPlans().stream().filter(educationPlan -> {
            return educationPlan.getValidFrom().compareTo(studyingDetails.getStartDate()) >= 0;
        }).min((ep1, ep2) -> ep1.getValidFrom().compareTo(ep2.getValidFrom())).get();
    }

}
