package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.exception.BadArgumentException;
import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.*;
import com.hegaro.medicalapp.repository.*;
import com.hegaro.medicalapp.service.ConsultationService;
import com.hegaro.medicalapp.service.dto.request.ConsultationExamRequest;
import com.hegaro.medicalapp.service.dto.request.ConsultationRequest;
import com.hegaro.medicalapp.service.dto.request.DetailConsultationsRequest;
import com.hegaro.medicalapp.service.dto.request.ExamIdsRequest;
import com.hegaro.medicalapp.service.dto.response.ConsultationExamResponse;
import com.hegaro.medicalapp.service.dto.response.ConsultationResponse;
import com.hegaro.medicalapp.service.dto.response.DetailConsultationResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Slf4j
@Service
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final ExamRepository examRepository;
    private final ConsultationExamRepository consultationExamRepository;
    private final ModelMapper modelMapper;
    private static final Logger LOGGER = Logger.getLogger(ConsultationServiceImpl.class.getName());
    public static final String PATIENT_NOT_FOUND_MESSAGE = "No se encuentra un paciente registrado con el ID : ";
    public static final String DOCTOR_NOT_FOUND_MESSAGE = "No se encuentra un doctor registrado con el ID : ";
    public static final String SPECIALTY_NOT_FOUND_MESSAGE = "No se encuentra una especialidad registrada con el ID : " ;
    public static final String EXAM_NOT_FOUND_MESSAGE = "No se encuentra un examen registrada con el ID : ";
    public static final String CONSULTATION_NOT_FOUND_MESSAGE  = "No se encuentra una consulta registrada con el ID : " ;
    public static final String DETAIL_CONSULTATIONS_NOT_FOUND_MESSAGE = "Debe registrar los detalles de la consulta de diagnóstico y tratamiento";

    public ConsultationServiceImpl(ConsultationRepository consultationRepository,
                                   PatientRepository patientRepository,
                                   DoctorRepository doctorRepository,
                                   SpecialtyRepository specialtyRepository,
                                   ConsultationExamRepository consultationExamRepository,
                                   ExamRepository examRepository,
                                   ModelMapper modelMapper) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
        this.consultationExamRepository = consultationExamRepository;
        this.examRepository = examRepository;
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
            throw new ModelNotFoundException(DETAIL_CONSULTATIONS_NOT_FOUND_MESSAGE);
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

    @Transactional
    @Override
    public ConsultationExamResponse registerTransactional(ConsultationExamRequest consultationExamRequest) {
        Optional<Patient> optPatient = patientRepository.findById(consultationExamRequest.getConsultationRequest().getPatientId());
        if(optPatient.isEmpty()){
            throw new ModelNotFoundException(PATIENT_NOT_FOUND_MESSAGE + consultationExamRequest.getConsultationRequest().getPatientId());
        }

        Optional<Doctor> optDoctor = doctorRepository.findById(consultationExamRequest.getConsultationRequest().getDoctorId());
        if(optDoctor.isEmpty()){
            throw new ModelNotFoundException(DOCTOR_NOT_FOUND_MESSAGE + consultationExamRequest.getConsultationRequest().getDoctorId());
        }

        Optional<Specialty>  optSpecialty = specialtyRepository.findById(consultationExamRequest.getConsultationRequest().getSpecialtyId());
        if(optSpecialty.isEmpty()){
            throw new ModelNotFoundException(SPECIALTY_NOT_FOUND_MESSAGE + consultationExamRequest.getConsultationRequest().getSpecialtyId());
        }

        if(consultationExamRequest.getConsultationRequest().getDetailConsultationsRequest().isEmpty()){
            throw new ModelNotFoundException(DETAIL_CONSULTATIONS_NOT_FOUND_MESSAGE);
        }

        Consultation newConsultation = new Consultation();
        newConsultation.setPatient(modelMapper.map(optPatient, Patient.class));
        newConsultation.setDoctor(modelMapper.map(optDoctor, Doctor.class));
        newConsultation.setSpecialty(modelMapper.map(optSpecialty, Specialty.class));
        newConsultation.setConsultationDate(consultationExamRequest.getConsultationRequest().getConsultationDate());
        List<DetailConsultation> detailConsultationEntityList = new LinkedList<>();
        for (DetailConsultationsRequest detailConsultationRequest: consultationExamRequest.getConsultationRequest().getDetailConsultationsRequest()) {
            DetailConsultation detailConsultationEntity = new DetailConsultation();
            detailConsultationEntity.setConsultation(newConsultation);
            detailConsultationEntity.setDiagnostic(detailConsultationRequest.getDiagnostic());
            detailConsultationEntity.setTreatment(detailConsultationRequest.getTreatment());
            detailConsultationEntityList.add(detailConsultationEntity);
        }

        newConsultation.setDetailConsultations(detailConsultationEntityList);
        Consultation consultation = consultationRepository.save(newConsultation);

        var examIdsList =  consultationExamRequest.getExamIdsRequest();
        if(!examIdsList.isEmpty()) {
            for(ExamIdsRequest examIdsRequest: examIdsList) {
                Optional<Exam>  optExam = examRepository.findById(examIdsRequest.getExamId());
                if(optExam.isEmpty()){
                    throw new ModelNotFoundException(EXAM_NOT_FOUND_MESSAGE + consultationExamRequest.getConsultationRequest().getSpecialtyId());
                }
            }

            for (var examId : examIdsList) {
                consultationExamRepository.register(consultation.getId(), examId.getExamId());
            }
        }

        return modelMapper.map(consultation, ConsultationExamResponse.class);
    }

    @Override
    public ConsultationResponse update(Long id, ConsultationRequest consultationRequest) {
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
            throw new ModelNotFoundException(DETAIL_CONSULTATIONS_NOT_FOUND_MESSAGE);
        }

        return consultationRepository.findById(id)
                .map(existingConsultation -> {
                    existingConsultation.setPatient(modelMapper.map(optPatient, Patient.class));
                    existingConsultation.setDoctor(modelMapper.map(optDoctor, Doctor.class));
                    existingConsultation.setSpecialty(modelMapper.map(optSpecialty, Specialty.class));
                    existingConsultation.setConsultationDate(consultationRequest.getConsultationDate());

                    List<DetailConsultation> detailConsultationsEntityList = existingConsultation.getDetailConsultations();
                    List<DetailConsultationsRequest> detailConsultationsRequestList = consultationRequest.getDetailConsultationsRequest();

                    // Update the elements in the order they are sent in the request
                    int minSize = Math.min(detailConsultationsEntityList.size(), detailConsultationsRequestList.size());
                    for (int i = 0; i < minSize; i++) {
                        DetailConsultationsRequest request = detailConsultationsRequestList.get(i);
                        DetailConsultation entity = detailConsultationsEntityList.get(i);

                        // Update the diagnostic and treatment attributes on the main object
                        entity.setDiagnostic(request.getDiagnostic());
                        entity.setTreatment(request.getTreatment());
                    }

                    // If the request list has more elements, add them to the end
                    for (int i = minSize; i < detailConsultationsRequestList.size(); i++) {
                        DetailConsultationsRequest request = detailConsultationsRequestList.get(i);
                        DetailConsultation newEntity = new DetailConsultation();
                        newEntity.setDiagnostic(request.getDiagnostic());
                        newEntity.setTreatment(request.getTreatment());
                        detailConsultationsEntityList.add(newEntity);
                    }

                    // If the entity list has more elements, remove the extra ones at the end
                    if (detailConsultationsEntityList.size() > detailConsultationsRequestList.size()) {
                        detailConsultationsEntityList.subList(detailConsultationsRequestList.size(), detailConsultationsEntityList.size()).clear();
                    }

                    // Save the updated query to the database
                    existingConsultation = consultationRepository.save(existingConsultation);

                    // Map the result to Consultation Response with the complete objects
                    ConsultationResponse response = new ConsultationResponse();
                    response.setId(existingConsultation.getId());
                    response.setPatient(existingConsultation.getPatient());
                    response.setDoctor(existingConsultation.getDoctor());
                    response.setSpecialty(existingConsultation.getSpecialty());
                    response.setConsultationDate(existingConsultation.getConsultationDate());
                    response.setDetailConsultationsResponse(modelMapper.map(detailConsultationsEntityList, new TypeToken<List<DetailConsultationResponse>>() {}.getType()));

                    return response;


                })
                .orElseThrow(() -> new ModelNotFoundException(CONSULTATION_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public List<ConsultationResponse> findAll() {
    List<Consultation> consultationEntityList = consultationRepository.findAll();
    if(consultationEntityList.isEmpty()){
        throw new ModelNotFoundException("No se encontraron registros de consultas");
    }
    List<ConsultationResponse> consultationResponseList = new LinkedList<>();

    for (Consultation consultationEntity: consultationEntityList) {
        ConsultationResponse consultationResponse = new ConsultationResponse();
        consultationResponse.setId(consultationEntity.getId());
        consultationResponse.setPatient(consultationEntity.getPatient());
        consultationResponse.setDoctor(consultationEntity.getDoctor());
        consultationResponse.setSpecialty(consultationEntity.getSpecialty());
        consultationResponse.setConsultationDate(consultationEntity.getConsultationDate());

        // Create a list of detailConsultationsResponse specific to each query
        List<DetailConsultationResponse> detailconsultationsResponseList = new LinkedList<>();
        for (DetailConsultation detailConsultation: consultationEntity.getDetailConsultations()) {
            DetailConsultationResponse detailConsultationResponse = new DetailConsultationResponse();
            detailConsultationResponse.setDiagnostic(detailConsultation.getDiagnostic());
            detailConsultationResponse.setTreatment(detailConsultation.getTreatment());
            detailconsultationsResponseList.add(detailConsultationResponse);
        }

        consultationResponse.setDetailConsultationsResponse(detailconsultationsResponseList);
        consultationResponseList.add(consultationResponse);
    }
    return consultationResponseList;
}

    @Override
    public ConsultationResponse findById(Long id) {
        if(id == null || id == 0) {
            throw new BadArgumentException("El parámetro no es válido");
        }

        var consultation = consultationRepository.findById(id);
        if(consultation.isEmpty()) {
            throw new ModelNotFoundException(CONSULTATION_NOT_FOUND_MESSAGE + id);
        }
        var details = consultation.get().getDetailConsultations();
        var consultationResponse = modelMapper.map(consultation, ConsultationResponse.class);
        boolean instanceExist = false;
        for (DetailConsultation detailConsultation: details){
            var detailConsultationResponse = modelMapper.map(detailConsultation, DetailConsultationResponse.class);
               if(!instanceExist){
                   consultationResponse.setDetailConsultationsResponse(new LinkedList<>());
                   instanceExist = true;
               }
               consultationResponse.getDetailConsultationsResponse().add(detailConsultationResponse);
        }

        return consultationResponse;
    }

    @Override
    public void delete(Long id) {
        var consultation =  consultationRepository.findById(id);
        if(consultation.isEmpty())
            throw new ModelNotFoundException(CONSULTATION_NOT_FOUND_MESSAGE + id);
        patientRepository.deleteById(id);
    }
}