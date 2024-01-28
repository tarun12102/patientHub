package com.straumann.patientHub.service.impl;

import com.straumann.patientHub.Repository.AppointmentRepository;
import com.straumann.patientHub.Repository.DoctorRepository;
import com.straumann.patientHub.Repository.PatientRepository;
import com.straumann.patientHub.dto.AppointmentDto;
import com.straumann.patientHub.entity.Appointment;
import com.straumann.patientHub.entity.Doctor;
import com.straumann.patientHub.entity.Patient;
import com.straumann.patientHub.exception.EntityAlreadyExistsException;
import com.straumann.patientHub.exception.ResourceNotFoundException;
import com.straumann.patientHub.mapper.AppointmentMapper;
import com.straumann.patientHub.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
@CacheConfig(cacheNames = {"appointments"})
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public AppointmentDto createAppointment(AppointmentDto appointmentDto) {
        Optional<Appointment> existingEntity = appointmentRepository.findById(appointmentDto.getAppointmentId());
        if(existingEntity.isPresent()){
            throw new EntityAlreadyExistsException("Appoint already exists with id : "+appointmentDto.getAppointmentId());
        }
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", appointmentDto.getPatientId()));
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", appointmentDto.getDoctorId()));
        Appointment appointment = AppointmentMapper.mapToAppointment(appointmentDto, patient, doctor);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return AppointmentMapper.mapToAppointmentDto(savedAppointment);
    }

    @Override
    @Cacheable(key = "#appointmentId")
    public AppointmentDto getAppointment(int appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", appointmentId));

        return AppointmentMapper.mapToAppointmentDto(appointment);
    }

    @Override
    @CachePut(key = "#appointmentDto.appointmentId")
    public AppointmentDto updateAppointment(AppointmentDto appointmentDto) {
        appointmentRepository.findById(appointmentDto.getAppointmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", appointmentDto.getAppointmentId()));
        Patient patient = patientRepository.findById(appointmentDto.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient", appointmentDto.getPatientId()));
        Doctor doctor = doctorRepository.findById(appointmentDto.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor", appointmentDto.getDoctorId()));
        Appointment appointment = AppointmentMapper.mapToAppointment(appointmentDto, patient, doctor);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return AppointmentMapper.mapToAppointmentDto(updatedAppointment);
    }

    @Override
    @CacheEvict(key = "#appointmentId")
    public boolean deleteAppointment(int appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment", appointmentId));
        appointmentRepository.deleteById(appointmentId);
        return true;
    }
}
