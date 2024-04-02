package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.controller.dto.request.ExamRequest;
import com.hegaro.medicalapp.controller.dto.request.PatientRequest;
import com.hegaro.medicalapp.controller.dto.response.ExamResponse;
import com.hegaro.medicalapp.controller.dto.response.PatientResponse;
import com.hegaro.medicalapp.model.Patient;

import java.util.List;

public interface PatientService {
    PatientResponse findByDocumentNumber(String documentNumber);
    PatientResponse register(PatientRequest examRequest);
    PatientResponse update(Long id, PatientRequest patientRequest);
    List<PatientResponse> findAll();
    PatientResponse findById(Long id);
    void delete(Long id);
}