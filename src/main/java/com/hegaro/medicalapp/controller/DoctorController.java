package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.exception.DuplicateDataException;
import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.Doctor;
import com.hegaro.medicalapp.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<Doctor>> findAll(){
        var doctors = doctorService.findAll();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Doctor> findById(@PathVariable("id") Integer id){
        var doctor = doctorService.findById(id);
        if(doctor == null){
            throw new ModelNotFoundException("No se encuentra un doctor con ID : " + id);
        }
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Doctor> register(@Valid @RequestBody Doctor doctor){
        var professionalCardFoundChecker = doctorService.findByProfessionalCard(doctor.getProfessionalCard());
        if(professionalCardFoundChecker != null){
            throw new DuplicateDataException("Ya se encuentra un médico  registrado con la tarjeta profesional : " + doctor.getProfessionalCard());
        }
        var doctorCreated = doctorService.register(doctor);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(doctorCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping
    public ResponseEntity<Doctor> update(@Valid @RequestBody Doctor doctor){
        var professionalCardFoundChecker = doctorService.findByProfessionalCard(doctor.getProfessionalCard());
        if((professionalCardFoundChecker!=null) && (!Objects.equals(professionalCardFoundChecker.getId(), doctor.getId()))){
            throw new DuplicateDataException("Ya se encuentra un médico  registrado con la tarjeta profesional : " + doctor.getProfessionalCard());
        }
        doctorService.update(doctor);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Doctor> delete(@PathVariable("id") Integer id){
        var doctor = doctorService.findById(id);
        if(doctor == null){
            throw new ModelNotFoundException("No se encuentra un doctor con ID : " + id);
        }
        doctorService.delete(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }
}