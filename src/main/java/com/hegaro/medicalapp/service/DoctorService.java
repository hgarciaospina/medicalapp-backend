package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.controller.dto.request.DoctorRequest;
import com.hegaro.medicalapp.controller.dto.response.DoctorResponse;
import com.hegaro.medicalapp.model.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Optional<Doctor> findByProfessionalCard(String professionalCard);
    DoctorResponse register(DoctorRequest doctorRequest);
    DoctorResponse update(Long id, DoctorRequest doctorRequest);
    List<DoctorResponse> findAll();
    DoctorResponse findById(Long id);
    void delete(Long id);
}