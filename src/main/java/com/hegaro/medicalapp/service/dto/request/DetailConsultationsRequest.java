package com.hegaro.medicalapp.service.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hegaro.medicalapp.model.Consultation;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DetailConsultationsRequest {
    @JsonIgnore
    private ConsultationRequest consultationRequest;
    @NotNull(message = "Debe ingresar el diagnóstico.")
    @NotEmpty(message = "El diagnóstico no debe estar vacío.")
    @NotBlank(message = "El diagnóstico no debe ir en blanco.")
    @Size(message = "El diagnóstico no debe exceder los 1000 caracteres.", max = 1000)
    private String diagnostic;
    @NotNull(message = "Debe ingresar el diagnóstico.")
    @NotEmpty(message = "El diagnóstico no debe estar vacío.")
    @NotBlank(message = "El diagnóstico no debe ir en blanco")
    @Size(message = "El diagnóstico no debe exceder los 1000 caracteres.", max = 1000)
    @Column(nullable = false, length = 1000)
    private String treatment;

    public DetailConsultationsRequest() {
    }

    public DetailConsultationsRequest(ConsultationRequest consultationRequest, String diagnostic, String treatment) {
        this.consultationRequest = consultationRequest;
        this.diagnostic = diagnostic;
        this.treatment = treatment;
    }

    public ConsultationRequest getConsultationRequest() {
        return consultationRequest;
    }

    public void setConsultationRequest(ConsultationRequest consultationRequest) {
        this.consultationRequest = consultationRequest;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }
}