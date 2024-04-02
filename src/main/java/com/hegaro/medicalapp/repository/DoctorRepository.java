package com.hegaro.medicalapp.repository;

import com.hegaro.medicalapp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Optional<Doctor> findByProfessionalCard(String professionalCard);
}