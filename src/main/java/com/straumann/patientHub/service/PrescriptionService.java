package com.straumann.patientHub.service;

import com.straumann.patientHub.dto.PrescriptionDto;

public interface PrescriptionService {
    PrescriptionDto createPrescription(PrescriptionDto prescriptionDto) ;

    PrescriptionDto getPrescription(int prescriptionId) ;

    PrescriptionDto updatePrescription(PrescriptionDto prescriptionDto);

    boolean deletePrescription(int prescriptionId);
}
