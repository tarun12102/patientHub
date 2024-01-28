package com.straumann.patientHub.mapper;

import com.straumann.patientHub.dto.PrescriptionDto;
import com.straumann.patientHub.entity.Appointment;
import com.straumann.patientHub.entity.Prescription;

public class PrescriptionMapper {

    public static Prescription mapToPrescription(PrescriptionDto prescriptionDto, Appointment appointment){
        return Prescription.builder().prescriptionId(prescriptionDto.getPrescriptionId())
                .testsSuggested(prescriptionDto.getTestsSuggested())
                .remarks(prescriptionDto.getRemarks())
                .appointment(appointment).build();
    }

    public static PrescriptionDto mapToPrescriptionDto(Prescription prescription){
        return PrescriptionDto.builder().prescriptionId(prescription.getPrescriptionId())
                .testsSuggested(prescription.getTestsSuggested())
                .remarks(prescription.getRemarks())
                .appointmentId(prescription.getAppointment().getAppointmentId()).build();
    }
}
