package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.controller.dto.request.PatientRequest;
import com.hegaro.medicalapp.controller.dto.response.PatientResponse;
import com.hegaro.medicalapp.exception.BadArgumentException;
import com.hegaro.medicalapp.exception.DuplicateDataException;
import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.Patient;
import com.hegaro.medicalapp.repository.PatientRepository;
import com.hegaro.medicalapp.service.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;
    public static final String PATIENT_NOT_FOUND_MESSAGE = "No se encuentra un paciente con ID : ";
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public PatientResponse register(PatientRequest patientRequest) {
        Optional<Patient> documentNumberAlreadyExists
                = patientRepository.findByDocumentNumber(patientRequest.getDocumentNumber());
        if(documentNumberAlreadyExists .isPresent()){
            throw new DuplicateDataException("Ya se encuentra un paciente  registrado con el número de documento : " + patientRequest.getDocumentNumber());
        }
        Patient newPatient = patientRepository.save(modelMapper.map(patientRequest, Patient.class));
        return modelMapper.map(newPatient, PatientResponse.class);
    }

    @Override
    public PatientResponse update(Long id, PatientRequest patientRequest) {
        Optional<Patient>  documentNumberAlreadyExists =
              patientRepository.findByDocumentNumber(patientRequest.getDocumentNumber());
        if((documentNumberAlreadyExists .isPresent())
                &&
           (!Objects.equals(documentNumberAlreadyExists.get().getId(), id))){
            throw new DuplicateDataException("Ya se encuentra un paciente  registrado con el número de documento : " + patientRequest.getDocumentNumber());
        }
        return patientRepository.findById(id)
                .map(existingPatient -> {
                    existingPatient.setFirstName(patientRequest.getFirstName());
                    existingPatient.setLastName(patientRequest.getLastName());
                    existingPatient.setDocumentNumber(patientRequest.getDocumentNumber());
                    existingPatient.setAddress(patientRequest.getAddress());
                    existingPatient.setPhoneNumber(patientRequest.getPhoneNumber());
                    existingPatient.setEmail(patientRequest.getEmail());
                    patientRepository.save(existingPatient);
                    return modelMapper.map(existingPatient, PatientResponse.class );
                })
                .orElseThrow(() -> new ModelNotFoundException(PATIENT_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public Optional<Patient> findByDocumentNumber(String documentNumber) {
        return patientRepository.findAll()
                .stream()
                .filter(patient -> documentNumber.equals(patient.getDocumentNumber()))
                .findFirst();
    }
    @Override
    public List<PatientResponse> findAll() {
        var patients = patientRepository.findAll()
                .stream()
                .map(patient -> new PatientResponse(
                        patient.getId(),
                        patient.getFirstName(),
                        patient.getLastName(),
                        patient.getDocumentNumber(),
                        patient.getAddress(),
                        patient.getPhoneNumber(),
                        patient.getEmail()))
                .toList();

        if(patients.isEmpty()) {
             throw new ModelNotFoundException("No se encontraron pacientes registrados. ");
        }
        return patients;
    }

    @Override
    public PatientResponse findById(Long id) {
        if(id == null || id == 0) {
            throw new BadArgumentException("El parámetro no es válido");
        }

        var patient = patientRepository.findById(id);
        if(patient.isEmpty()) {
            throw new ModelNotFoundException(PATIENT_NOT_FOUND_MESSAGE + id);
        }
        return modelMapper.map(patient, PatientResponse.class);
    }

    @Override
    public void delete(Long id) {
        var patient =  patientRepository.findById(id);
        if(patient.isEmpty())
                throw new ModelNotFoundException(PATIENT_NOT_FOUND_MESSAGE + id);
        patientRepository.deleteById(id);
    }
}