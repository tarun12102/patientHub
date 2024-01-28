package com.straumann.patientHub.service;

import com.straumann.patientHub.dto.DoctorDto;

public interface DoctorService {
    DoctorDto createDoctor(DoctorDto doctorDto);

    DoctorDto updateDoctor(DoctorDto doctorDto);

    DoctorDto getDoctor(int doctorId);

    boolean deleteDoctor(int doctorId);
}
