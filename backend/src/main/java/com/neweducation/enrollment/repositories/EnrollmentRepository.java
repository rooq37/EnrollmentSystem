package com.neweducation.enrollment.repositories;

import com.neweducation.enrollment.models.Enrollment;
import com.neweducation.enrollment.models.EnrollmentId;
import com.neweducation.enrollment.models.Group;
import com.neweducation.enrollment.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, EnrollmentId> {

    Optional<Enrollment> findByStudentAndGroup(Student student, Group group);

}
