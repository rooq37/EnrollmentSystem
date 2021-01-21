package com.neweducation.enrollment.repositories;

import com.neweducation.enrollment.models.EnrollmentBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EnrollmentBlockRepository extends JpaRepository<EnrollmentBlock, Long> {

    Optional<EnrollmentBlock> findById(Long id);

}
