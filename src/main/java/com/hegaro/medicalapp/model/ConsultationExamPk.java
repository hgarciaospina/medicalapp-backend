package com.hegaro.medicalapp.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ConsultationExamPk implements Serializable {
    @ManyToOne
    @JoinColumn(name = "consultation_id", nullable = false, foreignKey = @ForeignKey(name = "fk_consultation_exam_consultation_id"))
    private Consultation consultation;
    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false,  foreignKey = @ForeignKey(name = "fk_consultation_exam_exam_id"))
    private Exam exam;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsultationExamPk that)) return false;
        return Objects.equals(consultation, that.consultation) && Objects.equals(exam, that.exam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(consultation, exam);
    }
}