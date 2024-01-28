package com.straumann.patientHub.service;

import com.straumann.patientHub.dto.MedicalHistoryDto;

public interface MedicalHistoryService {
    MedicalHistoryDto createMedicalHistory(MedicalHistoryDto medicalHistoryDto);

    MedicalHistoryDto updateMedicalHistory(MedicalHistoryDto medicalHistoryDto);

    MedicalHistoryDto getMedicalHistory(int medicalHistoryId);

    boolean deleteMedicalHistory(int medicalHistoryId);
}
