package com.hegaro.medicalapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
public class Specialty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Size(max = 100, message = "El nombre de la especialidad debe tener máximo 100 caracteres.")
    @Column(nullable = false, length = 100)
    private String name;

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
}
