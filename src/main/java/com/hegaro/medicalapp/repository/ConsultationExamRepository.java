package com.hegaro.medicalapp.repository;

import com.hegaro.medicalapp.model.ConsultationExam;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ConsultationExamRepository extends JpaRepository<ConsultationExam, Long> {
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO consultation_exam(consultation_id, exam_id) VALUES (:consultationId, :examId)", nativeQuery = true)
    Integer register(@Param("consultationId") Long consultationId, @Param("examId") Long examId);
}
