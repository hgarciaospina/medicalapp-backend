package com.hegaro.medicalapp.model;

import jakarta.persistence.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 100, message = "El nombre(s) debe tener máximo 100 caracteres.")
    @Size(min = 3, message = "El nombre(s) debe tener mínimo 3 caracteres.")
    @Column(nullable = false, length = 100)
    private String firstName;
    @Size(max = 100, message = "El apellido(s) debe tener máximo 100 caracteres.")
    @Size(min = 3, message = "El apellido(s) debe tener mínimo 3 caracteres.")
    @Column(nullable = false, length = 100)
    private String lastName;
    @Size(max = 10, message = "El número de documento de identidad debe tener máximo 10 digitos.")
    @Size(min = 2, message = "El número de documento de identidad debe tener mínimo 2 digitos.")
    @Column(nullable = false, length = 10)
    private String  documentNumber;
    @Size(max = 300, message = "La dirección debe tener máximo 300 caracteres")
    @Size(min = 10, message = "La dirección debe tener mínimo 10 caracteres")
    @Column(nullable = true, length = 300)
    private String address;
    @Size(min=10, max = 10, message = "El número de dispositivo móvil debe tener 10 digitos.")
    @Column(nullable = false, length = 10)
    private String phoneNumber;
    @Email
    @Size(max = 100, message = "El correo electrónico debe tener máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

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