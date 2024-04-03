package com.hegaro.medicalapp.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ExamRequest {
    @NotNull(message = "Debe ingresar el nombre del examen.")
    @NotEmpty(message = "El nombre del examen no debe estar vacío.")
    @NotBlank(message = "El nombre  del examen no debe ir en blanco")
    @Size(max = 100, message = "El nombre del examen no debe exceder los 100 caracteres.")
    String name;
    @NotNull(message = "Debe ingresar la descripción del examen.")
    @NotEmpty(message = "La descripción del examen no debe estar vacía.")
    @NotBlank(message = "La descripción del examen no debe ir en blanco")
    @Size(max = 300, message = "La descripción del examen debe no debe exceder los 300 caracteres.")
    private String description;

    public ExamRequest() {
    }

    public ExamRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}