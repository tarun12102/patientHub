package com.straumann.patientHub.mapper;

import com.straumann.patientHub.dto.DoctorDto;
import com.straumann.patientHub.entity.Doctor;

public class DoctorMapper {

    public static Doctor mapToDoctor(DoctorDto doctorDto){

        return Doctor.builder().doctorId(doctorDto.getDoctorId())
                .name(doctorDto.getName())
                .email(doctorDto.getEmail())
                .mobileNumber(doctorDto.getMobileNumber())
                .address(doctorDto.getAddress())
                .age(doctorDto.getAge())
                .gender(doctorDto.getGender())
                .speciality(doctorDto.getSpeciality()).build();

    }

    public static DoctorDto mapToDoctorDto(Doctor doctor){
        return DoctorDto.builder().doctorId(doctor.getDoctorId())
                .name(doctor.getName())
                .email(doctor.getEmail())
                .mobileNumber(doctor.getMobileNumber())
                .address(doctor.getAddress())
                .age(doctor.getAge())
                .gender(doctor.getGender())
                .speciality(doctor.getSpeciality()).build();
    }
}
