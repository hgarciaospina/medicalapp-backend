package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.model.Doctor;
import com.hegaro.medicalapp.repository.DoctorRepository;
import com.hegaro.medicalapp.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor register(Doctor doctor) {
       return doctorRepository.save(doctor);
    }

    @Override
    public Doctor update(Doctor Doctor) {
        return doctorRepository.save(Doctor);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    @Override
    public Doctor findById(Integer id) {
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        return optionalDoctor.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        doctorRepository.deleteById(id);
    }
}