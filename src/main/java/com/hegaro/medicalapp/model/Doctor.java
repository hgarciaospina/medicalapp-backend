package com.hegaro.medicalapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Debe ingresar el nombre(s) del doctor.")
    @NotEmpty(message = "El nombre(s) del doctor no debe(n) estar vacío(s).")
    @NotBlank(message = "El nombre(s) del doctor no debe ir en blanco")
    @Size(message = "El nombre(s) del doctor no debe exceder los 100 caracteres.", max = 100)
    @Column(nullable = false, length = 100)
    private String firstName;
    @NotNull(message = "Debe ingresar el apellido(s) del doctor.")
    @NotEmpty(message = "El apellido(s) del doctor no debe(n) estar vacío(s).")
    @NotBlank(message = "El apellido(s) del doctor no debe ir en blanco")
    @Size(message = "El apellido(s) del doctor no debe exceder los 100 caracteres.", max = 100)
    @Column(nullable = false, length = 100)
    private String lastName;
    @NotNull(message = "Debe ingresar el número de la tarjeta profesional.")
    @NotEmpty(message = "El número de la tarjeta profesional no debe ir vacío.")
    @NotBlank(message = "El número de la tarjeta profesional no debe ir en blanco.")
    @Size(message = "El número de la tarjeta profesional debe tener 13 digitos.", max = 13, min = 13)
    @Column(nullable = false, length = 13)
    private String professionalCard;

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

    public String getProfessionalCard() {
        return professionalCard;
    }

    public void setProfessionalCard(String professionalCard) {
        this.professionalCard = professionalCard;
    }
}