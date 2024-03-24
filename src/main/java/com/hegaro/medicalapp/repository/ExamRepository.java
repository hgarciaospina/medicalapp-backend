package com.hegaro.medicalapp.repository;

import com.hegaro.medicalapp.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}