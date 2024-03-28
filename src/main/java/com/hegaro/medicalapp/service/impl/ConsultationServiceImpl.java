package com.hegaro.medicalapp.service.impl;

import com.hegaro.medicalapp.model.Consultation;
import com.hegaro.medicalapp.repository.ConsultationRepository;
import com.hegaro.medicalapp.service.ConsultationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationServiceImpl implements ConsultationService {
    private final ConsultationRepository consultationRepository;

    public ConsultationServiceImpl(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Consultation register(Consultation consultation) {
       consultation.getDetailConsultations().forEach(detail -> detail.setConsultation(consultation));
        return consultationRepository.save(consultation);
    }

    @Override
    public Consultation update(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    @Override
    public List<Consultation> findAll() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation findById(Integer id) {
        Optional<Consultation> optionalConsultation = consultationRepository.findById(id);
        return optionalConsultation.orElse(null);
    }

    @Override
    public void delete(Integer id) {
        consultationRepository.deleteById(id);
    }
}