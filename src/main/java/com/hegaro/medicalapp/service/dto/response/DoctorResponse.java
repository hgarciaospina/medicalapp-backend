package com.hegaro.medicalapp.service.dto.response;
public class DoctorResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String professionalCard;

    public DoctorResponse() {
    }

    public DoctorResponse(Long id, String firstName, String lastName, String professionalCard) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.professionalCard = professionalCard;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getProfessionalCard() {
        return professionalCard;
    }

    public void setProfessionalCard(String professionalCard) {
        this.professionalCard = professionalCard;
    }
}