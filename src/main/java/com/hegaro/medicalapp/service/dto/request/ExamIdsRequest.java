package com.hegaro.medicalapp.service.dto.request;
public class ExamIdsRequest {
    Long examId;

    public ExamIdsRequest() {
    }

    public ExamIdsRequest(Long examId) {
        this.examId = examId;
    }

    public Long getExamId() {
        return examId;
    }

    public void setExamId(Long examId) {
        this.examId = examId;
    }
}