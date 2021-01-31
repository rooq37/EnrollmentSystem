package com.neweducation.enrollment.services.enrollment_home;

import com.neweducation.enrollment.dtos.enrollment_home.CourseItemDTO;
import com.neweducation.enrollment.dtos.enrollment_home.EnrollmentBlockDTO;
import com.neweducation.enrollment.dtos.enrollment_home.EnrollmentDetailsDTO;
import com.neweducation.enrollment.dtos.enrollment_home.FieldOfStudyDTO;
import com.neweducation.enrollment.exceptions.not_found.StudentNotFoundException;
import com.neweducation.enrollment.models.*;
import com.neweducation.enrollment.repositories.FieldOfStudyRepository;
import com.neweducation.enrollment.repositories.StudentRepository;
import com.neweducation.enrollment.repositories.StudentScheduleRepository;
import com.neweducation.enrollment.repositories.StudyingDetailsRepository;
import com.neweducation.enrollment.services.EnrollmentMapper;
import com.neweducation.enrollment.services.EnrollmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentHomeService extends EnrollmentService {

    private final StudentScheduleRepository studentScheduleRepository;
    private final ModelMapper mapper;
    private final EnrollmentMapper homeMapper;

    public EnrollmentHomeService(StudentRepository studentRepository,
                                 StudentScheduleRepository studentScheduleRepository,
                                 StudyingDetailsRepository studyingDetailsRepository,
                                 FieldOfStudyRepository fieldOfStudyRepository) {
        super(studyingDetailsRepository, studentRepository, fieldOfStudyRepository);
        this.studentScheduleRepository = studentScheduleRepository;
        this.mapper = new ModelMapper();
        this.homeMapper = new EnrollmentMapper();
    }

    public List<FieldOfStudyDTO> getFieldsOfStudy(Long studentIndex) {
        Student student = studentRepository.findByIndex(studentIndex)
                .orElseThrow(() -> new StudentNotFoundException(String.valueOf(studentIndex)));
        return student.getStudyingDetails().stream().map(StudyingDetails::getFieldOfStudy)
                .map(fieldOfStudy -> mapper.map(fieldOfStudy, FieldOfStudyDTO.class))
                .collect(Collectors.toList());
    }

    public List<EnrollmentBlockDTO> getEnrollmentBlocks(Long studentIndex, String fieldOfStudyCode) {
        Student student = studentRepository.findByIndex(studentIndex)
                .orElseThrow(() -> new StudentNotFoundException(String.valueOf(studentIndex)));

        return student.getStudentSchedules().stream().map(StudentSchedule::getEnrollmentBlock)
                .filter(enrollmentBlock -> {
                    return enrollmentBlock.getFieldsOfStudy().stream()
                            .map(FieldOfStudy::getCode).collect(Collectors.toList()).contains(fieldOfStudyCode);
                }).map(enrollmentBlock -> mapper.map(enrollmentBlock, EnrollmentBlockDTO.class))
                .collect(Collectors.toList());
    }

    public EnrollmentDetailsDTO getEnrollmentDetails(Long studentIndex, Long enrollmentBlockId) {
        StudentSchedule studentSchedule = studentScheduleRepository.findByStudentIndexAndEnrollmentBlockId(studentIndex, enrollmentBlockId)
                .orElseThrow(() -> new RuntimeException("E"));

        return mapper.map(studentSchedule, EnrollmentDetailsDTO.class);
    }

    public List<CourseItemDTO> getCurrentCourses(Long studentIndex, String fieldOfStudyCode) {
        return getCourses(studentIndex, fieldOfStudyCode, CourseStatus.TO_PASS)
                .stream().map(homeMapper::mapToCourseItem)
                .collect(Collectors.toList());
    }

    public List<CourseItemDTO> getOverdueCourses(Long studentIndex, String fieldOfStudyCode) {
        return getCourses(studentIndex, fieldOfStudyCode, CourseStatus.FAILED)
                .stream().map(homeMapper::mapToCourseItem)
                .collect(Collectors.toList());
    }
}
