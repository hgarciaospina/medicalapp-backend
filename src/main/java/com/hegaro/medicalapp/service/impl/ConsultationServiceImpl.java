package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.*;
import com.hegaro.medicalapp.repository.ConsultationRepository;
import com.hegaro.medicalapp.repository.DoctorRepository;
import com.hegaro.medicalapp.repository.PatientRepository;
import com.hegaro.medicalapp.repository.SpecialtyRepository;
import com.hegaro.medicalapp.service.ConsultationService;
import com.hegaro.medicalapp.service.dto.request.ConsultationRequest;
import com.hegaro.medicalapp.service.dto.request.DetailConsultationsRequest;
import com.hegaro.medicalapp.service.dto.response.ConsultationResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final ModelMapper modelMapper;
    public static final String PATIENT_NOT_FOUND_MESSAGE = "No se encuentra un paciente registrado con el ID : ";
    public static final String DOCTOR_NOT_FOUND_MESSAGE = "No se encuentra un doctor registrado con el ID : ";
    public static final String SPECIALTY_NOT_FOUND_MESSAGE = "No se encuentra una especialidad registrada con el ID : " ;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository,
                                   PatientRepository patientRepository,
                                   DoctorRepository doctorRepository,
                                   SpecialtyRepository specialtyRepository,
                                   ModelMapper modelMapper) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public ConsultationResponse register(ConsultationRequest consultationRequest) {
        Optional<Patient> optPatient = patientRepository.findById(consultationRequest.getPatientId());
        if(optPatient.isEmpty()){
            throw new ModelNotFoundException(PATIENT_NOT_FOUND_MESSAGE + consultationRequest.getPatientId());
        }

        Optional<Doctor> optDoctor = doctorRepository.findById(consultationRequest.getDoctorId());
        if(optDoctor.isEmpty()){
            throw new ModelNotFoundException(DOCTOR_NOT_FOUND_MESSAGE + consultationRequest.getDoctorId());
        }

        Optional<Specialty>  optSpecialty = specialtyRepository.findById(consultationRequest.getSpecialtyId());
        if(optSpecialty.isEmpty()){
            throw new ModelNotFoundException(SPECIALTY_NOT_FOUND_MESSAGE + consultationRequest.getSpecialtyId());
        }

        if(consultationRequest.getDetailConsultationsRequest().isEmpty()){
            throw new ModelNotFoundException("Debe registrar los detalles de la consulta de diagnóstico y tratamiento");
        }

        Consultation newConsultation = new Consultation();
        newConsultation.setPatient(modelMapper.map(optPatient, Patient.class));
        newConsultation.setDoctor(modelMapper.map(optDoctor, Doctor.class));
        newConsultation.setSpecialty(modelMapper.map(optSpecialty, Specialty.class));
        newConsultation.setConsultationDate(consultationRequest.getConsultationDate());
        List<DetailConsultation> detailConsultationEntityList = new LinkedList<>();
        for (DetailConsultationsRequest detailConsultationRequest: consultationRequest.getDetailConsultationsRequest()) {
            DetailConsultation detailConsultationEntity = new DetailConsultation();
            detailConsultationEntity.setConsultation(newConsultation);
            detailConsultationEntity.setDiagnostic(detailConsultationRequest.getDiagnostic());
            detailConsultationEntity.setTreatment(detailConsultationRequest.getTreatment());
            detailConsultationEntityList.add(detailConsultationEntity);
        }

        newConsultation.setDetailConsultations(detailConsultationEntityList);
        Consultation consultation = consultationRepository.save(newConsultation);
        return modelMapper.map(consultation, ConsultationResponse.class);
    }
    /*
    @Override
    public Consultation update(Consultation consultation) {
        return consultationRepository.save(consultation);
    }
    @Override
    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }
    @Override
    public Consultation findById(Integer id) {
        Optional<Consultation> optionalConsultation = consultationRepository.findById(id);
        return optionalConsultation.orElse(null);
    }
    @Override
    public void delete(Integer id) {
        consultationRepository.deleteById(id);
    }
    */
}