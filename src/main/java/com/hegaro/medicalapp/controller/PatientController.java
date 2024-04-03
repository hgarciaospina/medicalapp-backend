package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.service.PatientService;
import com.hegaro.medicalapp.service.dto.request.PatientRequest;
import com.hegaro.medicalapp.service.dto.response.PatientResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
    public ResponseEntity<List<PatientResponse>> findAll(){
        var patients = patientService.findAll();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponse> findById(@PathVariable("id") Long id){
        var patient = patientService.findById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientResponse> register(@Valid @RequestBody PatientRequest patientRequest){
        var patientCreated = patientService.register(patientRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(patientCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PatientResponse> update(@PathVariable("id") Long id, @Valid @RequestBody PatientRequest patientRequest){
        patientService.update(id, patientRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PatientResponse> delete(@PathVariable("id") Long id){
        patientService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}