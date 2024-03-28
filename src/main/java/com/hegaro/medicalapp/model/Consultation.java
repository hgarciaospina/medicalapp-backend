package com.hegaro.medicalapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false,  foreignKey = @ForeignKey(name = "fk_consultation_patient"))
    private Patient patient;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false,  foreignKey = @ForeignKey(name = "fk_consultation_doctor"))
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "specialty_id", nullable = false,  foreignKey = @ForeignKey(name = "fk_consultation_specialty"))
    private Specialty specialty;

    @Column(name = "consultation_date", nullable = false)
    private LocalDateTime consultationDate;
    @OneToMany(mappedBy = "consultation", cascade = { CascadeType.PERSIST, CascadeType.MERGE,
    CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DetailConsultation> detailConsultations;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<DetailConsultation> getDetailConsultations() {
        return detailConsultations;
    }
    public void setDetailConsultations(List<DetailConsultation> detailConsultations) {
        this.detailConsultations = detailConsultations;
    }
}