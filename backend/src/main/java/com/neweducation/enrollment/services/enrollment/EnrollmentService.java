package com.neweducation.enrollment.services.enrollment;

import com.neweducation.enrollment.dtos.enrollment.EnrollmentBlocksDTO;
import com.neweducation.enrollment.exceptions.StudentNotFoundException;
import com.neweducation.enrollment.models.Student;
import com.neweducation.enrollment.repositories.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final StudentRepository studentRepository;
    private final EnrollmentMapper mapper;

    public EnrollmentService(StudentRepository studentRepository,
                             EnrollmentMapper enrollmentMapper) {
        this.studentRepository = studentRepository;
        this.mapper = enrollmentMapper;
    }

    public EnrollmentBlocksDTO getEnrollmentBlocks(String studentIndex) {
        Student student = studentRepository.findByIndex(Long.valueOf(studentIndex))
                .orElseThrow(() -> new StudentNotFoundException(studentIndex));

        return mapper.getEnrollmentBlocksDTO(student);
    }

}
