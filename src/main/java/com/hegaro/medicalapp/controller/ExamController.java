package com.hegaro.medicalapp.controller;

import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.Exam;
import com.hegaro.medicalapp.service.ExamService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/Exams")
public class ExamController {
    private final ExamService examService;
    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    public ResponseEntity<List<Exam>> findAll(){
        var exams = examService.findAll();
        return new ResponseEntity<>(exams, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Exam> findById(@PathVariable("id") Integer id){
        var exam = examService.findById(id);
        if(exam == null){
            throw new ModelNotFoundException("No se encuentra un Examen con ID : " + id);
        }
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Exam> register(@Valid @RequestBody Exam exam){
        var examCreated =examService.register(exam);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(examCreated.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @PutMapping
    public ResponseEntity<Exam> update(@Valid @RequestBody Exam exam){
        examService.update(exam);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Exam> delete(@PathVariable("id") Integer id){
        var exam = examService.findById(id);
        if(exam == null){
            throw new ModelNotFoundException("No se encuentra un Examen con ID : " + id);
        }
        examService.delete(id);
        return new ResponseEntity<>(exam, HttpStatus.OK);
    }
}