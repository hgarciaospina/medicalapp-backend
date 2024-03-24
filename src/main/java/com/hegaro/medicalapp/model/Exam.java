package com.hegaro.medicalapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Debe ingresar el nombre del examen.")
    @NotEmpty(message = "El nombre del examen no debe estar vacío.")
    @NotBlank(message = "El nombre  del examen no debe ir en blanco")
    @Size(max = 100, message = "El nombre del examen no debe exceder los 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String name;
    @NotNull(message = "Debe ingresar la descripción del examen.")
    @NotEmpty(message = "La descripción del examen no debe estar vacía.")
    @NotBlank(message = "La descripción del examen no debe ir en blanco")
    @Size(max = 300, message = "La descripción del examen debe no debe exceder los 300 caracteres.")
    @Column(nullable = false, length = 300)
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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