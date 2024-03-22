package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.model.Patient;

import java.util.List;

public interface PatientService {
    Patient register(Patient patient);
    void update(Patient patient);
    List<Patient> findAll();
    Patient findById(Integer id);
    void delete(Integer id);
}