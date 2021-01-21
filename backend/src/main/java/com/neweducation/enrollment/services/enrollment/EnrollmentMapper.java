package com.neweducation.enrollment.services.enrollment;

import com.neweducation.enrollment.dtos.enrollment.EnrollmentBlocksDTO;
import com.neweducation.enrollment.models.EnrollmentBlock;
import com.neweducation.enrollment.models.Student;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EnrollmentMapper {

    private final ModelMapper modelMapper;

    public EnrollmentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public EnrollmentBlocksDTO getEnrollmentBlocksDTO(Student student) {
        EnrollmentBlocksDTO dto = new EnrollmentBlocksDTO();
        dto.setEnrollmentBlockNames(student.getEnrollmentBlocks().stream()
                .map(EnrollmentBlock::getName).collect(Collectors.toList()));
        return dto;
    }

}
