package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.exception.DuplicateDataException;
import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.Patient;
import com.hegaro.medicalapp.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public ResponseEntity<List<Patient>> findAll(){
        var patients = patientService.findAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Patient> findById(@PathVariable("id") Integer id){
        var patient = patientService.findById(id);
        if(patient == null){
            throw new ModelNotFoundException("No se encuentra un paciente con ID : " + id);
        }
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Patient> register(@Valid @RequestBody Patient patient){
        var documentNumberFoundChecker = patientService.findByDocumentNumber(patient.getDocumentNumber());
        if(documentNumberFoundChecker != null){
            throw new DuplicateDataException("Ya se encuentra un paciente  registrado con el número de documento : " + patient.getDocumentNumber());
        }
        var patientCreated = patientService.register(patient);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patientCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping
    public ResponseEntity<Patient> update(@Valid @RequestBody Patient patient){
        patientService.update(patient);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Patient> delete(@PathVariable("id") Integer id){
        var patient = patientService.findById(id);
        if(patient == null){
            throw new ModelNotFoundException("No se encuentra un paciente con ID : " + id);
        }
        patientService.delete(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }
}