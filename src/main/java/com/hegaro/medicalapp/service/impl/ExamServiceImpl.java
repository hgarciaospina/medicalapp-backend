package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.model.Exam;
import com.hegaro.medicalapp.repository.ExamRepository;
import com.hegaro.medicalapp.service.ExamService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Exam register(Exam exam) {
       return examRepository.save(exam);
    }

    @Override
    public Exam update(Exam exam) {
        return examRepository.save(exam);
    }

    @Override
    public List<Exam> findAll() {
        return examRepository.findAll();
    }

    @Override
    public Exam findById(Integer id) {
        Optional<Exam> optionalExam = examRepository.findById(id);
        return optionalExam.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        examRepository.deleteById(id);
    }
}