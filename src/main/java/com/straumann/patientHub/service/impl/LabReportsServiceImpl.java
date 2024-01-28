package com.straumann.patientHub.service.impl;

import com.straumann.patientHub.Repository.LabReportsRepository;
import com.straumann.patientHub.Repository.PatientRepository;
import com.straumann.patientHub.dto.LabReportsDto;
import com.straumann.patientHub.entity.LabReports;
import com.straumann.patientHub.entity.Patient;
import com.straumann.patientHub.exception.EntityAlreadyExistsException;
import com.straumann.patientHub.exception.ResourceNotFoundException;
import com.straumann.patientHub.mapper.LabReportsMapper;
import com.straumann.patientHub.service.LabReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@CacheConfig(cacheNames = {"labReports"})
public class LabReportsServiceImpl implements LabReportsService {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    LabReportsRepository labReportsRepository;
    @Override
    public LabReportsDto createLabReport(LabReportsDto labReportsDto) {

        Optional<LabReports> existingEntity = labReportsRepository.findById(labReportsDto.getLabReportId());
        if(existingEntity.isPresent())
        {
            throw new EntityAlreadyExistsException("labreport already exists with this id ="+labReportsDto.getLabReportId());
        }
        Patient patient = patientRepository.findById(labReportsDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", labReportsDto.getPatientId()));
        LabReports labReports = LabReportsMapper.mapToLabReports(labReportsDto, patient);
        LabReports savedLabReports = labReportsRepository.save(labReports);
        return LabReportsMapper.mapToLabReportsDto(savedLabReports);
    }

    @Override
    @Cacheable(key = "#labReportId")
    public LabReportsDto getLabReport(int labReportId) {
        LabReports labReport = labReportsRepository.findById(labReportId)
                .orElseThrow(() -> new ResourceNotFoundException("LabReport", labReportId));
        return LabReportsMapper.mapToLabReportsDto(labReport);
    }

    @Override
    @CachePut(key = "#labReportsDto.labReportId")
    public LabReportsDto updateLabReport(LabReportsDto labReportsDto) {
        labReportsRepository.findById(labReportsDto.getLabReportId())
                .orElseThrow(() -> new ResourceNotFoundException("LabReport", labReportsDto.getLabReportId()));
        Patient patient = patientRepository.findById(labReportsDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", labReportsDto.getPatientId()));
        LabReports labReports = LabReportsMapper.mapToLabReports(labReportsDto, patient);
        LabReports savedLabReports = labReportsRepository.save(labReports);
        return LabReportsMapper.mapToLabReportsDto(savedLabReports);
    }

    @Override
    @CacheEvict(key = "#labReportId")
    public boolean deleteLabReport(int labReportId) {
        labReportsRepository.findById(labReportId)
                .orElseThrow(() -> new ResourceNotFoundException("LabReport", labReportId));
        labReportsRepository.deleteById(labReportId);
        return true;
    }
}
