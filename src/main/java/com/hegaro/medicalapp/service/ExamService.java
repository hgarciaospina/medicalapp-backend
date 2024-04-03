package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.service.dto.request.ExamRequest;
import com.hegaro.medicalapp.service.dto.response.ExamResponse;

import java.util.List;

public interface ExamService {
    ExamResponse register(ExamRequest examRequest);
    ExamResponse update(Long id, ExamRequest examRequest);
    List<ExamResponse> findAll();
    ExamResponse findById(Long id);
    void delete(Long id);
}