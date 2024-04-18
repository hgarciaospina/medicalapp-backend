package com.hegaro.medicalapp.service.dto.request;

import java.util.Set;

public class ConsultationExamRequest {
   private ConsultationRequest consultationRequest;
   private Set<ExamIdsRequest> examIdsRequest;
   public ConsultationExamRequest() {
    }

    public ConsultationExamRequest(ConsultationRequest consultationRequest, Set<ExamIdsRequest> examIdsRequest) {
        this.consultationRequest = consultationRequest;
        this.examIdsRequest = examIdsRequest;
    }

    public ConsultationRequest getConsultationRequest() {
        return consultationRequest;
    }

    public void setConsultationRequest(ConsultationRequest consultationRequest) {
        this.consultationRequest = consultationRequest;
    }

    public Set<ExamIdsRequest> getExamIdsRequest() {
        return examIdsRequest;
    }

    public void setExamIdsRequest(Set<ExamIdsRequest> examIdsRequest) {
        this.examIdsRequest = examIdsRequest;
    }
}