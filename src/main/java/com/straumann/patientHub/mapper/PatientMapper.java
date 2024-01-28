package com.straumann.patientHub.mapper;

import com.straumann.patientHub.dto.PatientDto;
import com.straumann.patientHub.entity.Patient;

public class PatientMapper {

    public static Patient mapToPatient(PatientDto patientDto)
    {
        return Patient.builder()
                .patientId(patientDto.getPatientId())
                .age(patientDto.getAge())
                .email(patientDto.getEmail())
                .name(patientDto.getName())
                .mobileNumber(patientDto.getMobileNumber())
                .address(patientDto.getAddress())
                .gender(patientDto.getGender())
                .build();
    }

    public static PatientDto mapToPatientDto(Patient patient)
    {
        return PatientDto.builder().patientId(patient.getPatientId())
                .name(patient.getName())
                .email(patient.getEmail())
                .mobileNumber(patient.getMobileNumber())
                .address(patient.getAddress())
                .age(patient.getAge())
                .gender(patient.getGender()).build();
    }
}
