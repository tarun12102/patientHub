package com.straumann.patientHub.service;

import com.straumann.patientHub.dto.AppointmentDto;
import com.straumann.patientHub.entity.Appointment;

import java.util.Date;

public interface AppointmentService {
    AppointmentDto createAppointment(AppointmentDto appointmentDto);

    AppointmentDto getAppointment(int appointmentId);

    AppointmentDto updateAppointment(AppointmentDto appointmentDto);

    boolean deleteAppointment(int appointmentId);
}
