package com.hegaro.medicalapp.repository;

import com.hegaro.medicalapp.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
    Doctor findByProfessionalCard(String professionalCard);
}