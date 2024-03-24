package com.hegaro.medicalapp.service;

import com.hegaro.medicalapp.model.Patient;

public interface PatientService extends CrudGeneric<Patient> {
    Patient findByDocumentNumber(String documentNumber);
}