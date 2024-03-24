package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.Specialty;
import com.hegaro.medicalapp.service.SpecialtyService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {
    private final SpecialtyService specialtyService;
    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping
    public ResponseEntity<List<Specialty>> findAll(){
        var specialties = specialtyService.findAll();
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Specialty> findById(@PathVariable("id") Integer id){
        var specialty = specialtyService.findById(id);
        if(specialty == null){
            throw new ModelNotFoundException("No se encuentra una epecialidad con ID : " + id);
        }
        return new ResponseEntity<>(specialty, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Specialty> register(@Valid @RequestBody Specialty specialty){
        var specialtyCreated = specialtyService.register(specialty);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(specialtyCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping
    public ResponseEntity<Specialty> update(@Valid @RequestBody Specialty specialty){
        specialtyService.update(specialty);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Specialty> delete(@PathVariable("id") Integer id){
        var specialty = specialtyService.findById(id);
        if(specialty == null){
            throw new ModelNotFoundException("No se encuentra una especialidad con ID : " + id);
        }
        specialtyService.delete(id);
        return new ResponseEntity<>(specialty, HttpStatus.OK);
    }
}