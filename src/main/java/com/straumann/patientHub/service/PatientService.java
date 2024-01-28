package com.straumann.patientHub.service;

import com.straumann.patientHub.dto.PatientDto;

public interface PatientService {
    PatientDto createPatient(PatientDto patientDto);

    PatientDto getPatient(int patientId);

    PatientDto updatePatient(PatientDto patientDto);

    boolean deletePatient(int patientId);
}
