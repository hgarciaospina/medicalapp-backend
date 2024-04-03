package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.exception.BadArgumentException;
import com.hegaro.medicalapp.exception.DuplicateDataException;
import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.Doctor;
import com.hegaro.medicalapp.repository.DoctorRepository;
import com.hegaro.medicalapp.service.DoctorService;
import com.hegaro.medicalapp.service.dto.request.DoctorRequest;
import com.hegaro.medicalapp.service.dto.response.DoctorResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final ModelMapper modelMapper;
    public static final String DOCTOR_NOT_FOUND_MESSAGE = "No se encuentra un doctor con ID : ";
    public DoctorServiceImpl(DoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DoctorResponse register(DoctorRequest doctorRequest) {
        Optional<Doctor> professionalCardAlreadyExists
                = doctorRepository.findByProfessionalCard(doctorRequest.getProfessionalCard());

        if(professionalCardAlreadyExists.isPresent()){
            throw new DuplicateDataException("Ya se encuentra un doctor registrado con el número de tarjeta profesional : " + doctorRequest.getProfessionalCard());
        }

        Doctor newDoctor = doctorRepository.save(modelMapper.map(doctorRequest, Doctor.class));
        return modelMapper.map(newDoctor, DoctorResponse.class);
    }

    @Override
    public DoctorResponse update(Long id, DoctorRequest doctorRequest) {
        Optional<Doctor>  professionalCardAlreadyExists =
              doctorRepository.findByProfessionalCard(doctorRequest.getProfessionalCard());
        if((professionalCardAlreadyExists.isPresent())
                &&
           (!Objects.equals(professionalCardAlreadyExists.get().getId(), id))){
            throw new DuplicateDataException("Ya se encuentra un doctor registrado con la tarjeta profesional: " + doctorRequest.getProfessionalCard());
        }
        return doctorRepository.findById(id)
                .map(existingDoctor -> {
                    existingDoctor.setFirstName(doctorRequest.getFirstName());
                    existingDoctor.setLastName(doctorRequest.getLastName());
                    existingDoctor.setProfessionalCard(doctorRequest.getProfessionalCard());
                    doctorRepository.save(existingDoctor);
                    return modelMapper.map(existingDoctor, DoctorResponse.class );
                })
                .orElseThrow(() -> new ModelNotFoundException(DOCTOR_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public Optional<Doctor> findByProfessionalCard(String professionalCard) {
        return doctorRepository.findAll()
                .stream()
                .filter(doctor -> professionalCard.equals(doctor.getProfessionalCard()))
                .findFirst();
    }

    @Override
    public List<DoctorResponse> findAll() {
        var doctors = doctorRepository.findAll()
                .stream()
                .map(doctor -> new DoctorResponse(
                        doctor.getId(),
                        doctor.getFirstName(),
                        doctor.getLastName(),
                        doctor.getProfessionalCard()))
                .toList();

        if(doctors.isEmpty()) {
             throw new ModelNotFoundException("No se encontraron dcotores registrados. ");
        }
        return doctors;
    }

    @Override
    public DoctorResponse findById(Long id) {
        if(id == null || id == 0) {
            throw new BadArgumentException("El parámetro no es válido");
        }

        var doctor = doctorRepository.findById(id);
        if(doctor.isEmpty()) {
            throw new ModelNotFoundException(DOCTOR_NOT_FOUND_MESSAGE + id);
        }
        return modelMapper.map(doctor, DoctorResponse.class);
    }

    @Override
    public void delete(Long id) {
        var doctor =  doctorRepository.findById(id);
        if(doctor.isEmpty())
                throw new ModelNotFoundException(DOCTOR_NOT_FOUND_MESSAGE + id);
        doctorRepository.deleteById(id);
    }
}