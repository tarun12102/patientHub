package com.straumann.patientHub.service.impl;

import com.straumann.patientHub.Repository.PatientRepository;
import com.straumann.patientHub.dto.PatientDto;
import com.straumann.patientHub.entity.Patient;
import com.straumann.patientHub.exception.EntityAlreadyExistsException;
import com.straumann.patientHub.exception.ResourceNotFoundException;
import com.straumann.patientHub.mapper.PatientMapper;
import com.straumann.patientHub.service.PatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"patients"})
public class PatientServiceImpl implements PatientService {

    Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Autowired
    PatientRepository patientRepository;
    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Optional<Patient> existingEntity = patientRepository.findById(patientDto.getPatientId());

        if(existingEntity.isPresent())
        {
            throw new EntityAlreadyExistsException("Patient already exists with this given id = "+patientDto.getPatientId());
        }
        Patient patient = PatientMapper.mapToPatient(patientDto);
        Patient savedPatient = patientRepository.save(patient);
        return PatientMapper.mapToPatientDto(savedPatient);
    }

    @Override
    @Cacheable(key = "#patientId")
    public PatientDto getPatient(int patientId) {

        logger.info("getting patient info from database");
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient", patientId));

        return PatientMapper.mapToPatientDto(patient);
    }

    @Override
    @CachePut(key = "#patientDto.patientId")
    public PatientDto updatePatient(PatientDto patientDto) {

        logger.info("Updating the cache");
        Patient patient = PatientMapper.mapToPatient(patientDto);
        patientRepository.findById(patient.getPatientId()).orElseThrow(() -> new ResourceNotFoundException("Patient", patient.getPatientId()));
        Patient savedPatient = patientRepository.save(patient);
        return PatientMapper.mapToPatientDto(savedPatient);
    }

    @Override
    @CacheEvict(key = "#patientId")
    public boolean deletePatient(int patientId) {
        logger.info("deleting from cache");
        patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient", patientId));
        patientRepository.deleteById(patientId);
        return true;
    }
}
