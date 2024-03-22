package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.model.Patient;
import com.hegaro.medicalapp.repository.PatientRepository;
import com.hegaro.medicalapp.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient register(Patient patient) {
       return patientRepository.save(patient);
    }

    @Override
    public void update(Patient patient) {
        patientRepository.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findById(Integer id) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        return optionalPatient.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        patientRepository.deleteById(id);
    }
}