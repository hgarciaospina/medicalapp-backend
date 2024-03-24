package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.model.Specialty;
import com.hegaro.medicalapp.repository.SpecialtyRepository;
import com.hegaro.medicalapp.service.SpecialtyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public Specialty register(Specialty specialty) {
       return specialtyRepository.save(specialty);
    }

    @Override
    public Specialty update(Specialty specialty) {
        return specialtyRepository.save(specialty);
    }

    @Override
    public List<Specialty> findAll() {
        return specialtyRepository.findAll();
    }

    @Override
    public Specialty findById(Integer id) {
        Optional<Specialty> optionalSpecialty = specialtyRepository.findById(id);
        return optionalSpecialty.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        specialtyRepository.deleteById(id);
    }
}