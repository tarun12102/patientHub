package com.straumann.patientHub.service.impl;


import com.straumann.patientHub.Repository.AppointmentRepository;
import com.straumann.patientHub.Repository.PrescriptionRepository;
import com.straumann.patientHub.dto.PrescriptionDto;
import com.straumann.patientHub.entity.Appointment;
import com.straumann.patientHub.entity.Prescription;
import com.straumann.patientHub.exception.EntityAlreadyExistsException;
import com.straumann.patientHub.exception.ResourceNotFoundException;
import com.straumann.patientHub.mapper.PrescriptionMapper;
import com.straumann.patientHub.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"prescriptions"})
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    PrescriptionRepository prescriptionRepository;
    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public PrescriptionDto createPrescription(PrescriptionDto prescriptionDto) {

        Optional<Prescription> existingEntity = prescriptionRepository.findById(prescriptionDto.getPrescriptionId());

        if(existingEntity.isPresent())
        {
            throw new EntityAlreadyExistsException("Prescription is already exists with given id = "+prescriptionDto.getPrescriptionId());
        }
        Appointment appointment = appointmentRepository.findById(prescriptionDto.getAppointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", prescriptionDto.getAppointmentId()));

        Prescription prescription = PrescriptionMapper.mapToPrescription(prescriptionDto, appointment);
        Prescription savedPrescription = prescriptionRepository.save(prescription);
        return PrescriptionMapper.mapToPrescriptionDto(savedPrescription);
    }

    @Override
    @Cacheable("#prescriptionId")
    public PrescriptionDto getPrescription(int prescriptionId) {
        Prescription prescription = prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", prescriptionId));

        return PrescriptionMapper.mapToPrescriptionDto(prescription);
    }

    @Override
    @CachePut("#prescriptionDto.prescriptionId")
    public PrescriptionDto updatePrescription(PrescriptionDto prescriptionDto) {
        prescriptionRepository.findById(prescriptionDto.getPrescriptionId())
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", prescriptionDto.getPrescriptionId()));
        Appointment appointment = appointmentRepository.findById(prescriptionDto.getAppointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", prescriptionDto.getAppointmentId()));
        Prescription prescription = PrescriptionMapper.mapToPrescription(prescriptionDto, appointment);
        Prescription updatedPrescription = prescriptionRepository.save(prescription);
        return PrescriptionMapper.mapToPrescriptionDto(updatedPrescription);

    }

    @Override
    @CacheEvict("#prescriptionId")
    public boolean deletePrescription(int prescriptionId) {
        prescriptionRepository.findById(prescriptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Prescription", prescriptionId));


        prescriptionRepository.deleteById(prescriptionId);
        return true;
    }
}
