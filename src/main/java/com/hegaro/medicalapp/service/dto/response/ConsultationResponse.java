package com.hegaro.medicalapp.service.dto.response;

import com.hegaro.medicalapp.model.DetailConsultation;
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
    private List<DetailConsultation> detailConsultations;

    public Long getId() {
        return id;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public Specialty getSpecialty() {
        return specialty;
    }

    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public List<DetailConsultation> getDetailConsultations() {
        return detailConsultations;
    }
}