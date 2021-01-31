package com.neweducation.enrollment.repositories;

import com.neweducation.enrollment.models.FieldOfStudy;
import com.neweducation.enrollment.models.Student;
import com.neweducation.enrollment.models.StudyingDetails;
import com.neweducation.enrollment.models.StudyingDetailsId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudyingDetailsRepository extends JpaRepository<StudyingDetails, StudyingDetailsId> {

    Optional<StudyingDetails> findStudyingDetailsByStudentAndFieldOfStudy(Student student, FieldOfStudy fieldOfStudy);

}
