package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.controller.dto.request.ExamRequest;
import com.hegaro.medicalapp.controller.dto.response.ExamResponse;
import com.hegaro.medicalapp.service.ExamService;
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
@RequestMapping("/exams")
public class ExamController {
    private final ExamService examService;
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public ResponseEntity<List<ExamResponse>> findAll(){
        var exams = examService.findAll();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamResponse> findById(@PathVariable("id") Long id){
        var exam = examService.findById(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ExamResponse> register(@Valid @RequestBody ExamRequest examRequest){
        var examCreated = examService.register(examRequest);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(examCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExamResponse> update(@PathVariable("id") Long id, @Valid @RequestBody ExamRequest examRequest){
        examService.update(id, examRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ExamResponse> delete(@PathVariable("id") Long id){
        examService.delete(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}