package com.neweducation.enrollment.services.enrollment;

import com.neweducation.enrollment.dtos.enrollment.EnrollmentBlockDTO;
import com.neweducation.enrollment.dtos.enrollment.EnrollmentDetailsDTO;
import com.neweducation.enrollment.dtos.enrollment.FieldOfStudyDTO;
import com.neweducation.enrollment.exceptions.StudentNotFoundException;
import com.neweducation.enrollment.models.FieldOfStudy;
import com.neweducation.enrollment.models.Student;
import com.neweducation.enrollment.models.StudentSchedule;
import com.neweducation.enrollment.models.StudyingDetails;
import com.neweducation.enrollment.repositories.StudentRepository;
import com.neweducation.enrollment.repositories.StudentScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrollmentService {

    private final StudentRepository studentRepository;
    private final StudentScheduleRepository studentScheduleRepository;
    private final ModelMapper mapper;

    public EnrollmentService(StudentRepository studentRepository,
                             StudentScheduleRepository studentScheduleRepository) {
        this.studentRepository = studentRepository;
        this.studentScheduleRepository = studentScheduleRepository;
        this.mapper = new ModelMapper();
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
}
