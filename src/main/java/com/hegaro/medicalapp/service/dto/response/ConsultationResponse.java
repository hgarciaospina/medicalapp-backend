package com.hegaro.medicalapp.service.dto.response;

import com.hegaro.medicalapp.model.Doctor;
import com.hegaro.medicalapp.model.Patient;
import com.hegaro.medicalapp.model.Specialty;

import java.time.LocalDateTime;
import java.util.List;
public class ConsultationResponse {
    private Long id;
    private Patient patient;
    private Doctor doctor;
    private Specialty specialty;
    private LocalDateTime consultationDate;
    private List<DetailConsultationResponse> detailConsultationsResponse;

    public ConsultationResponse() {
    }

    public ConsultationResponse(Long id, Patient patient, Doctor doctor,
                                Specialty specialty, LocalDateTime consultationDate,
                                List<DetailConsultationResponse> detailConsultationsResponse) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.specialty = specialty;
        this.consultationDate = consultationDate;
        this.detailConsultationsResponse = detailConsultationsResponse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public void setSpecialty(Specialty specialty) {
        this.specialty = specialty;
    }

    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDateTime consultationDate) {
        this.consultationDate = consultationDate;
    }

    public List<DetailConsultationResponse> getDetailConsultationsResponse() {
        return detailConsultationsResponse;
    }

    public void setDetailConsultationsResponse(List<DetailConsultationResponse> detailConsultationsResponse) {
        this.detailConsultationsResponse = detailConsultationsResponse;
    }
}