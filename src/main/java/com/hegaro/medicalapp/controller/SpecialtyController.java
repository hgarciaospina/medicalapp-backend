package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.service.SpecialtyService;
import com.hegaro.medicalapp.service.dto.request.SpecialtyRequest;
import com.hegaro.medicalapp.service.dto.response.SpecialtyResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;;

@RestController
@RequestMapping("/specialties")
public class SpecialtyController {
    private final SpecialtyService specialtyService;
    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping
    public ResponseEntity<List<SpecialtyResponse>> findAll(){
        var specialties = specialtyService.findAll();
        return new ResponseEntity<>(specialties, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<SpecialtyResponse> findById(@PathVariable("id") Long id){
        var specialty = specialtyService.findById(id);
        return new ResponseEntity<>(specialty, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<SpecialtyResponse> register(@Valid @RequestBody SpecialtyRequest specialtyRequest){
        var specialtyCreated = specialtyService.register(specialtyRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(specialtyCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<SpecialtyResponse> update(@PathVariable("id") Long id, @Valid @RequestBody SpecialtyRequest specialtyRequest){
        specialtyService.update(id, specialtyRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<SpecialtyResponse> delete(@PathVariable("id") Long id){
        specialtyService.delete(id);
        return new ResponseEntity<>(null,
                HttpStatus.OK);
    }
}