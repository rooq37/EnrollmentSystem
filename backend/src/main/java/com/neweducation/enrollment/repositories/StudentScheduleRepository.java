package com.neweducation.enrollment.repositories;

import com.neweducation.enrollment.models.StudentSchedule;
import com.neweducation.enrollment.models.StudentScheduleId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentScheduleRepository extends JpaRepository<StudentSchedule, StudentScheduleId> {

    Optional<StudentSchedule> findByStudentIndexAndEnrollmentBlockId(Long studentIndex, Long enrollmentBlockId);

}
