package com.hegaro.medicalapp.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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
    CascadeType.REMOVE}, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<DetailConsultation> detailConsultations;
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

    public List<DetailConsultation> getDetailConsultations() {
        return detailConsultations;
    }
    public void setDetailConsultations(List<DetailConsultation> detailConsultations) {
        this.detailConsultations = detailConsultations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Consultation that)) return false;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}