package com.straumann.patientHub.mapper;

import com.straumann.patientHub.dto.MedicalHistoryDto;
import com.straumann.patientHub.entity.MedicalHistory;
import com.straumann.patientHub.entity.Patient;

public class MedicalHistoryMapper {

    public static MedicalHistory mapToMedicalHistory(MedicalHistoryDto medicalHistoryDto, Patient patient){
       return MedicalHistory.builder().medicalHistoryId(medicalHistoryDto.getMedicalHistoryId())
                .diseaseName(medicalHistoryDto.getDiseaseName())
                .fromDate(medicalHistoryDto.getFromDate())
                .toDate(medicalHistoryDto.getToDate())
                .treatmentUsed(medicalHistoryDto.getTreatmentUsed())
                .patient(patient).build();
    }


    public static MedicalHistoryDto mapToMedicalHistoryDto(MedicalHistory medicalHistory) {
        return MedicalHistoryDto.builder().medicalHistoryId(medicalHistory.getMedicalHistoryId())
                .diseaseName(medicalHistory.getDiseaseName())
                .fromDate(medicalHistory.getFromDate())
                .toDate(medicalHistory.getToDate())
                .treatmentUsed(medicalHistory.getTreatmentUsed())
                .patientId(medicalHistory.getPatient().getPatientId()).build();
    }
}
