package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.controller.dto.request.ExamRequest;
import com.hegaro.medicalapp.controller.dto.response.ExamResponse;

import java.util.List;

public interface ExamService {
    ExamResponse register(ExamRequest examRequest);
    ExamResponse update(Long id, ExamRequest examRequest);
    List<ExamResponse> findAll();
    ExamResponse findById(Long id);
    void delete(Long id);
}