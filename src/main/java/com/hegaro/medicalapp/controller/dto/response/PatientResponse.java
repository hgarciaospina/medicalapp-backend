package com.hegaro.medicalapp.controller.dto.response;

import jakarta.validation.constraints.*;

public class PatientResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String  documentNumber;
    private String address;
    private String phoneNumber;
    private String email;

    public PatientResponse() {
    }

    public PatientResponse(Long id, String firstName, String lastName, String documentNumber, String address, String phoneNumber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.documentNumber = documentNumber;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }
}