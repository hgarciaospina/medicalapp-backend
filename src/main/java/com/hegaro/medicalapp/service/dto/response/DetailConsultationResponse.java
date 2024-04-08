package com.hegaro.medicalapp.service.dto.response;
public class DetailConsultationResponse {
    private String diagnostic;
    private String treatment;

    public DetailConsultationResponse() {
    }

    public DetailConsultationResponse(String diagnostic, String treatment) {
        this.diagnostic = diagnostic;
        this.treatment = treatment;
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