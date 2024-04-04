package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.Consultation;
import com.hegaro.medicalapp.service.ConsultationService;
import com.hegaro.medicalapp.service.dto.request.ConsultationRequest;
import com.hegaro.medicalapp.service.dto.response.ConsultationResponse;
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
@RequestMapping("/consultations")
public class ConsultationController {
    private final ConsultationService consultationService;
    public ConsultationController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }
/*
    @GetMapping
    public ResponseEntity<List<Consultation>> findAll(){
        var consultations = consultationService.findAll();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Consultation> findById(@PathVariable("id") Integer id){
        var consultation = consultationService.findById(id);
        if(consultation == null){
            throw new ModelNotFoundException("No se encuentra una consulta con ID : " + id);
        }
        return new ResponseEntity<>(consultation, HttpStatus.OK);
    } */
    @PostMapping
    public ResponseEntity<ConsultationResponse> register(@Valid @RequestBody ConsultationRequest consultationRequest){
        var consultationCreated = consultationService.register(consultationRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consultationCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    /*
    @PutMapping
    public ResponseEntity<Consultation> update(@Valid @RequestBody Consultation consultation){
        consultationService.update(consultation);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Consultation> delete(@PathVariable("id") Integer id){
        var consultation = consultationService.findById(id);
        if(consultation == null){
            throw new ModelNotFoundException("No se encuentra un consultation con ID : " + id);
        }
        consultationService.delete(id);
        return new ResponseEntity<>(consultation, HttpStatus.OK);
    }

     */
}