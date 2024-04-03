package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.model.Doctor;
import com.hegaro.medicalapp.service.dto.request.DoctorRequest;
import com.hegaro.medicalapp.service.dto.response.DoctorResponse;

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