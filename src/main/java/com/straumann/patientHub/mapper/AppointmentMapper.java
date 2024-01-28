package com.straumann.patientHub.mapper;

import com.straumann.patientHub.dto.AppointmentDto;
import com.straumann.patientHub.entity.Appointment;
import com.straumann.patientHub.entity.Doctor;
import com.straumann.patientHub.entity.Patient;

public class AppointmentMapper {

    public static Appointment mapToAppointment(AppointmentDto appointmentDto, Patient patient, Doctor doctor){

        return Appointment.builder().appointmentId(appointmentDto.getAppointmentId())
                .patient(patient)
                .doctor(doctor)
                .dateOfAppointment(appointmentDto.getDateOfAppointment()).build();

    }

    public static AppointmentDto mapToAppointmentDto(Appointment appointment){
        return AppointmentDto.builder().appointmentId(appointment.getAppointmentId())
                .patientId(appointment.getPatient().getPatientId())
                .doctorId(appointment.getDoctor().getDoctorId())
                .dateOfAppointment(appointment.getDateOfAppointment()).build();
    }
}
