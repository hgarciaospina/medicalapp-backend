package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.service.dto.request.ConsultationExamRequest;
import com.hegaro.medicalapp.service.dto.request.ConsultationRequest;
import com.hegaro.medicalapp.service.dto.response.ConsultationExamResponse;
import com.hegaro.medicalapp.service.dto.response.ConsultationResponse;

import java.util.List;

public interface ConsultationService {
    ConsultationResponse register(ConsultationRequest consultationRequest);
    ConsultationExamResponse registerTransactional(ConsultationExamRequest consultationExamRequest);

    ConsultationResponse update(Long id, ConsultationRequest consultationRequest);
    List<ConsultationResponse> findAll();
    ConsultationResponse findById(Long id);
    void delete(Long id);
}