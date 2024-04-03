package com.hegaro.medicalapp.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SpecialtyRequest {
    @NotNull(message = "Debe ingresar el nombre de la especialidad.")
    @NotEmpty(message = "El nombre de la especialidad no debe(n) estar vacía .")
    @NotBlank(message = "El nombre  de la especialidad no debe ir en blanco")
    @Size(max = 100, message = "El nombre de la especialidad no debe exceder los 100 caracteres.")
    String name;

    public SpecialtyRequest() {
    }

    public SpecialtyRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}