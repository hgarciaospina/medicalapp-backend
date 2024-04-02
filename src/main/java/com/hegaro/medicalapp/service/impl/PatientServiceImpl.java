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
        var documentNumberFoundChecker = this.findByDocumentNumber(patientRequest.getDocumentNumber());
        if(documentNumberFoundChecker != null){
            throw new DuplicateDataException("Ya se encuentra un paciente  registrado con el número de documento : " + patientRequest.getDocumentNumber());
        }
        Patient newPatient = new Patient(
                null,
                patientRequest.getFirstName(), patientRequest.getLastName(),
                patientRequest.getDocumentNumber(), patientRequest.getAddress(),
                patientRequest.getPhoneNumber(), patientRequest.getEmail());
        newPatient = patientRepository.save(newPatient);
        return modelMapper.map(newPatient, PatientResponse.class);
    }

    @Override
    public PatientResponse update(Long id, PatientRequest patientRequest) {
        var documentNumberFoundChecker = this.findByDocumentNumber(patientRequest.getDocumentNumber());
        if((documentNumberFoundChecker!=null)
                &&
           (!Objects.equals(documentNumberFoundChecker.getId(), id))){
            throw new DuplicateDataException("Ya se encuentra un paciente  registrado con el número de documento : " + id);
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
    public PatientResponse findByDocumentNumber(String documentNumber) {
        PatientResponse  documentNumberFoundChecker;
        documentNumberFoundChecker = patientRepository.findByDocumentNumber(documentNumber);
        return documentNumberFoundChecker;
    }
    @Override
    public List<PatientResponse> findAll() {
        var patients = patientRepository.findAll()
                .stream()
                .map(patient -> new PatientResponse(
                        patient.getId(),
                        patient.getFirstName(),
                        patient.getLastName(),
                        patient.getAddress(),
                        patient.getDocumentNumber(),
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