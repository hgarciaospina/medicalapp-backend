package com.hegaro.medicalapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Debe ingresar el nombre(s) del paciente.")
    @NotEmpty(message = "El nombre(s) del paciente no debe(n) estar vacío(s).")
    @NotBlank(message = "El nombre(s) del paciente no debe ir en blanco")
    @Size(max = 100, message = "El nombre(s) del paciente debe tener máximo 100 caracteres.")
    @Size(min = 3, message = "El nombre(s) del paciente debe tener mínimo 3 caracteres.")
    @Column(nullable = false, length = 100)
    private String firstName;
    @NotNull(message = "Debe ingresar el apellido(s) del paciente.")
    @NotEmpty(message = "El apellido(s) del paciente no debe(n) estar vacío(s).")
    @NotBlank(message = "El apellido(s) del paciente no debe ir en blanco")
    @Size(max = 100, message = "El apellido(s) del páciente debe tener máximo 100 caracteres.")
    @Size(min = 3, message = "El apellido(s) del páciente debe tener mínimo 3 caracteres.")
    @Column(nullable = false, length = 100)
    private String lastName;
    @Size(max = 10, message = "El número de documento de identidad debe tener máximo 10 digitos.")
    @Size(min = 2, message = "El número de documento de identidad debe tener mínimo 2 digitos.")
    @Column(nullable = false, length = 10)
    private String  documentNumber;
    @Size(max = 300, message = "La dirección debe tener máximo 300 caracteres")
    @Size(min = 10, message = "La dirección debe tener mínimo 10 caracteres")
    @Column(nullable = false, length = 300)
    private String address;
    @Size(min=10, max = 10, message = "El número de dispositivo móvil debe tener 10 digitos.")
    @Column(nullable = false, length = 10)
    private String phoneNumber;
    @Email
    @Size(max = 100, message = "El correo electrónico debe tener máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String email;

    public Patient() {
    }

    public Patient(Long id, String firstName, String lastName,
                   String documentNumber, String address,
                   String phoneNumber, String email) {
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

    public String getDocumentNumber() { return documentNumber; }

    public void setDocumentNumber(String documentNumber) { this.documentNumber = documentNumber; }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}