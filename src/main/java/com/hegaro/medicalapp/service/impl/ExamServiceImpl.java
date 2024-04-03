package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.exception.BadArgumentException;
import com.hegaro.medicalapp.exception.ModelNotFoundException;
import com.hegaro.medicalapp.model.Exam;
import com.hegaro.medicalapp.repository.ExamRepository;
import com.hegaro.medicalapp.service.ExamService;
import com.hegaro.medicalapp.service.dto.request.ExamRequest;
import com.hegaro.medicalapp.service.dto.response.ExamResponse;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {
    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;
    public static final String EXAM_NOT_FOUND_MESSAGE = "No se encuentra un examen con ID : ";
    public ExamServiceImpl(ExamRepository examRepository, ModelMapper modelMapper) {
        this.examRepository = examRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ExamResponse register(ExamRequest examRequest) {
        Exam newExam = new Exam(null, examRequest.getName(), examRequest.getDescription());
        newExam = examRepository.save(newExam);
        return modelMapper.map(newExam, ExamResponse.class);
    }

    @Override
    public ExamResponse update(Long id, ExamRequest examRequest) {
        return examRepository.findById(id)
                .map(existingExam -> {
                    existingExam.setName(examRequest.getName());
                    existingExam.setDescription(examRequest.getDescription());
                    examRepository.save(existingExam);
                    return modelMapper.map(existingExam, ExamResponse.class );
                })
                .orElseThrow(() -> new ModelNotFoundException(EXAM_NOT_FOUND_MESSAGE + id));
    }

    @Override
    public List<ExamResponse> findAll() {
        var exams = examRepository.findAll()
                .stream()
                .map(exam -> new ExamResponse(exam.getId(), exam.getName(), exam.getDescription()))
                .toList();

        if(exams.isEmpty()) {
             throw new ModelNotFoundException("No se encontraron examenes registradas. ");
        }
        return exams;
    }

    @Override
    public ExamResponse findById(Long id) {
        if(id == null || id == 0) {
            throw new BadArgumentException("El parámetro no es válido");
        }

        var exam = examRepository.findById(id);
        if(exam.isEmpty()) {
            throw new ModelNotFoundException(EXAM_NOT_FOUND_MESSAGE + id);
        }
        return modelMapper.map(exam, ExamResponse.class);
    }

    @Override
    public void delete(Long id) {
        var exam =  examRepository.findById(id);
        if(exam.isEmpty())
                throw new ModelNotFoundException(EXAM_NOT_FOUND_MESSAGE + id);
        examRepository.deleteById(id);
    }
}