package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.model.Patient;
import com.hegaro.medicalapp.service.dto.request.PatientRequest;
import com.hegaro.medicalapp.service.dto.response.PatientResponse;

import java.util.List;
import java.util.Optional;

public interface PatientService {
    Optional<Patient> findByDocumentNumber(String documentNumber);
    PatientResponse register(PatientRequest patientRequest);
    PatientResponse update(Long id, PatientRequest patientRequest);
    List<PatientResponse> findAll();
    PatientResponse findById(Long id);
    void delete(Long id);
}