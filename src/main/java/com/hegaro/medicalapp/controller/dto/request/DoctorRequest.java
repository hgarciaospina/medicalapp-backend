package com.hegaro.medicalapp.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DoctorRequest {
    @NotNull(message = "Debe ingresar el nombre(s) del doctor.")
    @NotEmpty(message = "El nombre(s) del doctor no debe(n) estar vacío(s).")
    @NotBlank(message = "El nombre(s) del doctor no debe ir en blanco")
    @Size(message = "El nombre(s) del doctor no debe exceder los 100 caracteres.", max = 100)
    private String firstName;
    @NotNull(message = "Debe ingresar el apellido(s) del doctor.")
    @NotEmpty(message = "El apellido(s) del doctor no debe(n) estar vacío(s).")
    @NotBlank(message = "El apellido(s) del doctor no debe ir en blanco")
    @Size(message = "El apellido(s) del doctor no debe exceder los 100 caracteres.", max = 100)
    private String lastName;
    @NotNull(message = "Debe ingresar el número de la tarjeta profesional.")
    @NotEmpty(message = "El número de la tarjeta profesional no debe ir vacío.")
    @NotBlank(message = "El número de la tarjeta profesional no debe ir en blanco.")
    @Size(message = "El número de la tarjeta profesional debe tener 13 digitos.", max = 13, min = 13)
    private String professionalCard;

    public DoctorRequest() {
    }

    public DoctorRequest(String firstName, String lastName, String professionalCard) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.professionalCard = professionalCard;
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