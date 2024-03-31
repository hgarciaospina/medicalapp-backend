package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.controller.dto.request.SpecialtyRequest;
import com.hegaro.medicalapp.controller.dto.response.SpecialtyResponse;

import java.util.List;

public interface SpecialtyService {
    SpecialtyResponse register(SpecialtyRequest specialtyRequest);
    SpecialtyResponse update(Long id, SpecialtyRequest specialtyRequest);
    List<SpecialtyResponse> findAll();
    SpecialtyResponse  findById(Long id);
    void delete(Long id);
}