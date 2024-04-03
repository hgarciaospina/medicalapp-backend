package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.exception.BadArgumentException;
import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.Specialty;
import com.hegaro.medicalapp.repository.SpecialtyRepository;
import com.hegaro.medicalapp.service.SpecialtyService;
import com.hegaro.medicalapp.service.dto.request.SpecialtyRequest;
import com.hegaro.medicalapp.service.dto.response.SpecialtyResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SpecialtyServiceImpl implements SpecialtyService {
    private final SpecialtyRepository specialtyRepository;
    private final ModelMapper modelMapper;
    public static final String SPECIALTY_NOT_FOUND_MESSAGE = "No se encuentra una epecialidad con ID : ";
    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository, ModelMapper modelMapper) {
        this.specialtyRepository = specialtyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public SpecialtyResponse register(SpecialtyRequest specialtyRequest) {
        Specialty newSpeciality = new Specialty(null, specialtyRequest.getName());
        newSpeciality = specialtyRepository.save(newSpeciality);
        return modelMapper.map(newSpeciality, SpecialtyResponse.class);
    }

    @Override
    public SpecialtyResponse update(Long id, SpecialtyRequest specialtyRequest) {
        return specialtyRepository.findById(id)
                .map(existingSpecialty -> {
                    existingSpecialty.setName(specialtyRequest.getName());
                    specialtyRepository.save(existingSpecialty);
                    return modelMapper.map(existingSpecialty,SpecialtyResponse.class );
                })
                .orElseThrow(() -> new ModelNotFoundException( SPECIALTY_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public List<SpecialtyResponse> findAll() {
        var specialities = specialtyRepository.findAll()
                .stream()
                .map(specialty -> new SpecialtyResponse(specialty.getId(), specialty.getName()))
                .toList();

        if(specialities.isEmpty()) {
             throw new ModelNotFoundException("No se encontraron especialiades registradas. ");
        }
        return specialities;
    }

    @Override
    public SpecialtyResponse findById(Long id) {
        if(id == null || id == 0) {
            throw new BadArgumentException("El parámetro no es válido");
        }

        var specialty = specialtyRepository.findById(id);
        if(specialty.isEmpty()) {
            throw new ModelNotFoundException(SPECIALTY_NOT_FOUND_MESSAGE + id);
        }
        return modelMapper.map(specialty, SpecialtyResponse.class);
    }

    @Override
    public void delete(Long id) {
        var specialty =  specialtyRepository.findById(id);
        if(specialty.isEmpty())
                throw new ModelNotFoundException(SPECIALTY_NOT_FOUND_MESSAGE + id);
        specialtyRepository.deleteById(id);
    }
}