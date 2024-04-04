package com.hegaro.medicalapp.repository;

import com.hegaro.medicalapp.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}