package com.hegaro.medicalapp.repository;

import com.hegaro.medicalapp.model.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecialtyRepository extends JpaRepository<Specialty, Integer> {
}