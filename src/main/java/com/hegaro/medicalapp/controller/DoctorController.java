package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.service.DoctorService;
import com.hegaro.medicalapp.service.dto.request.DoctorRequest;
import com.hegaro.medicalapp.service.dto.response.DoctorResponse;
import com.hegaro.medicalapp.service.dto.response.PatientResponse;
import jakarta.validation.Valid;
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

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponse>> findAll(){
        var doctors = doctorService.findAll();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponse> findById(@PathVariable("id") Long id){
        var doctor = doctorService.findById(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PatientResponse> register(@Valid @RequestBody DoctorRequest doctorRequest){
        var doctorCreated = doctorService.register(doctorRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(doctorCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponse> update(@PathVariable("id") Long id, @Valid @RequestBody DoctorRequest doctorRequest){
        doctorService.update(id, doctorRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DoctorResponse> delete(@PathVariable("id") Long id){
        doctorService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}