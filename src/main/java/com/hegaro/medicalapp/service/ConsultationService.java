package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.service.dto.request.ConsultationRequest;
import com.hegaro.medicalapp.service.dto.request.ExamRequest;
import com.hegaro.medicalapp.service.dto.response.ConsultationResponse;

import java.util.List;

public interface ConsultationService {
    ConsultationResponse register(ConsultationRequest consultationRequest);
 //   ConsultationResponse update(Long id, ExamRequest examRequest);
 //   List<ConsultationResponse> findAll();
 //   ConsultationResponse findById(Long id);
 //   void delete(Long id);

}