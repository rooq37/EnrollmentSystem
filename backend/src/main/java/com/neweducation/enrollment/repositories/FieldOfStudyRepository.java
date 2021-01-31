package com.neweducation.enrollment.repositories;

import com.neweducation.enrollment.models.FieldOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FieldOfStudyRepository extends JpaRepository<FieldOfStudy, String> {

    Optional<FieldOfStudy> findByCode(String code);

}

