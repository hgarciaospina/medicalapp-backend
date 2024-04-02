package com.hegaro.medicalapp.repository;

import com.hegaro.medicalapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByDocumentNumber(String documentNumber);
}