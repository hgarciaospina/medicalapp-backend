package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.service.ConsultationService;
import com.hegaro.medicalapp.service.dto.request.ConsultationExamRequest;
import com.hegaro.medicalapp.service.dto.request.ConsultationRequest;
import com.hegaro.medicalapp.service.dto.response.ConsultationExamResponse;
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

    @GetMapping
    public ResponseEntity<List<ConsultationResponse>> findAll(){
        var consultations = consultationService.findAll();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationResponse> findById(@PathVariable("id") Long id) {
        ConsultationResponse consultationResponse = consultationService.findById(id);
        return new ResponseEntity<>(consultationResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ConsultationExamResponse> register(@Valid @RequestBody ConsultationExamRequest consultationExamRequest){
        var consultationCreated = consultationService.registerTransactional(consultationExamRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(consultationCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<ConsultationResponse> update(@PathVariable("id") Long id, @Valid @RequestBody ConsultationRequest consultationRequest){
        ConsultationResponse consultationResponse = consultationService.update(id, consultationRequest);
        return new ResponseEntity<>(consultationResponse, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ConsultationResponse> delete(@PathVariable("id") Long id){
        consultationService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}