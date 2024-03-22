package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.model.Patient;
import com.hegaro.medicalapp.service.PatientService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    public List<Patient> findAll(){
        return patientService.findAll();
    }
    @GetMapping("/{id}")
    public Patient findById(@PathVariable("id") Integer id){
        return patientService.findById(id);
    }
    @PostMapping
    public void register(@Valid @RequestBody Patient patient){
        patientService.register(patient);
    }
    @PutMapping
    public void update(@Valid @RequestBody Patient patient){
        patientService.update(patient);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id){
        patientService.delete(id);
    }
}