package com.hegaro.medicalapp.service.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
public class ConsultationRequest {
    @NotNull(message = "Debe asignar un paciente")
    private Long patientId;
    @NotNull(message = "Debe asignar un doctor")
    private Long doctorId;
    @NotNull(message = "Debe asignar una especialidad")
    private Long specialtyId;
    @NotNull(message = "Debe asignar una fecha")
    private LocalDateTime consultationDate;
    @NotNull(message = "Debe ingresar los detalles de la consulta")
    private List<DetailConsultationsRequest> detailConsultationsRequest;

    public ConsultationRequest() {
    }

    public ConsultationRequest(Long patientId, Long doctorId,
                               Long specialtyId, LocalDateTime consultationDate,
                               List<DetailConsultationsRequest> detailConsultationsRequest) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.specialtyId = specialtyId;
        this.consultationDate = consultationDate;
        this.detailConsultationsRequest = detailConsultationsRequest;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public Long getSpecialtyId() {
        return specialtyId;
    }

    public void setSpecialtyId(Long specialtyId) {
        this.specialtyId = specialtyId;
    }

    public LocalDateTime getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(LocalDateTime consultationDate) {
        this.consultationDate = consultationDate;
    }

    public List<DetailConsultationsRequest> getDetailConsultationsRequest() {
        return detailConsultationsRequest;   }

    public void setDetailConsultationsRequest(List<DetailConsultationsRequest> detailConsultationsRequest) {
        this.detailConsultationsRequest = detailConsultationsRequest;
    }
}