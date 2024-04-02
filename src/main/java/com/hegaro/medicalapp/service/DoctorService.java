package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.model.Doctor;

import java.util.Optional;

public interface DoctorService {
    Optional<Doctor> findByProfessionalCard(String professionalCard);
}