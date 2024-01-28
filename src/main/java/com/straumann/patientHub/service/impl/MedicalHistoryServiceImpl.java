package com.straumann.patientHub.service.impl;

import com.straumann.patientHub.Repository.MedicalHistoryRepository;
import com.straumann.patientHub.Repository.PatientRepository;
import com.straumann.patientHub.dto.MedicalHistoryDto;
import com.straumann.patientHub.entity.MedicalHistory;
import com.straumann.patientHub.entity.Patient;
import com.straumann.patientHub.exception.EntityAlreadyExistsException;
import com.straumann.patientHub.exception.ResourceNotFoundException;
import com.straumann.patientHub.mapper.MedicalHistoryMapper;
import com.straumann.patientHub.service.MedicalHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@CacheConfig(cacheNames = {"medicalHistory"})
public class MedicalHistoryServiceImpl implements MedicalHistoryService {

    @Autowired
    PatientRepository patientRepository;
    @Autowired
    MedicalHistoryRepository medicalHistoryRepository;

    @Override
    public MedicalHistoryDto createMedicalHistory(MedicalHistoryDto medicalHistoryDto) {
        Optional<MedicalHistory> existingEntity = medicalHistoryRepository.findById(medicalHistoryDto.getMedicalHistoryId());
        if(existingEntity.isPresent())
        {
            throw new EntityAlreadyExistsException("Medical history is already present with this given id = "+medicalHistoryDto.getMedicalHistoryId());
        }

        Patient patient = patientRepository.findById(medicalHistoryDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", medicalHistoryDto.getPatientId()));
        MedicalHistory medicalHistory = MedicalHistoryMapper.mapToMedicalHistory(medicalHistoryDto, patient);
        MedicalHistory savedMedicalHistory = medicalHistoryRepository.save(medicalHistory);
        return MedicalHistoryMapper.mapToMedicalHistoryDto(savedMedicalHistory);
    }

    @Override
    @CachePut(key="#medicalHistoryDto.medicalHistoryId")
    public MedicalHistoryDto updateMedicalHistory(MedicalHistoryDto medicalHistoryDto) {
        medicalHistoryRepository.findById(medicalHistoryDto.getMedicalHistoryId())
                .orElseThrow(() -> new ResourceNotFoundException("MedicalHistory", medicalHistoryDto.getMedicalHistoryId()));
        Patient patient = patientRepository.findById(medicalHistoryDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", medicalHistoryDto.getPatientId()));
        MedicalHistory medicalHistory = MedicalHistoryMapper.mapToMedicalHistory(medicalHistoryDto, patient);
        MedicalHistory updatedMedicalHistory = medicalHistoryRepository.save(medicalHistory);
        return MedicalHistoryMapper.mapToMedicalHistoryDto(updatedMedicalHistory);
    }

    @Override
    @Cacheable(key = "#medicalHistoryId")
    public MedicalHistoryDto getMedicalHistory(int medicalHistoryId) {
        MedicalHistory medicalHistory = medicalHistoryRepository.findById(medicalHistoryId)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalHistory", medicalHistoryId));

        return MedicalHistoryMapper.mapToMedicalHistoryDto(medicalHistory);
    }

    @Override
    @CacheEvict(key = "#medicalHistoryId")
    public boolean deleteMedicalHistory(int medicalHistoryId) {
        medicalHistoryRepository.findById(medicalHistoryId)
                .orElseThrow(() -> new ResourceNotFoundException("MedicalHistory", medicalHistoryId));
        medicalHistoryRepository.deleteById(medicalHistoryId);
        return true;
    }
}
