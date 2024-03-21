package com.hegaro.medicalapp.repository;

import com.hegaro.medicalapp.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}