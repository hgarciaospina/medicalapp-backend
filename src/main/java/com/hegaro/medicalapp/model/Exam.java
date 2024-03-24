package com.hegaro.medicalapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 100, message = "El nombre del examen tener máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String name;
    @Size(max = 100, message = "La descripción del examen debe tener máximo 300 caracteres.")
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