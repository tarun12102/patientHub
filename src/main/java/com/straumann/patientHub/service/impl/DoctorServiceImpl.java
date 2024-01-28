package com.straumann.patientHub.service.impl;

import com.straumann.patientHub.Repository.DoctorRepository;
import com.straumann.patientHub.dto.DoctorDto;
import com.straumann.patientHub.entity.Doctor;
import com.straumann.patientHub.exception.EntityAlreadyExistsException;
import com.straumann.patientHub.exception.ResourceNotFoundException;
import com.straumann.patientHub.mapper.DoctorMapper;
import com.straumann.patientHub.service.DoctorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@CacheConfig(cacheNames = {"doctors"})
public class DoctorServiceImpl implements DoctorService {

    Logger logger = LoggerFactory.getLogger(DoctorServiceImpl.class);
    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {

        Optional<Doctor> existingEntity = doctorRepository.findById(doctorDto.getDoctorId());

        if(existingEntity.isPresent())
        {
            throw new EntityAlreadyExistsException("Doctor with given id exists, id = "+doctorDto.getDoctorId());
        }
        Doctor doctor = DoctorMapper.mapToDoctor(doctorDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return DoctorMapper.mapToDoctorDto(savedDoctor);
    }

    @Override
    @CachePut(key = "#doctorDto.doctorId")
    public DoctorDto updateDoctor(DoctorDto doctorDto) {
        logger.info("updating cache");
        Doctor doctor = DoctorMapper.mapToDoctor(doctorDto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return DoctorMapper.mapToDoctorDto(savedDoctor);
    }

    @Override
    @Cacheable(key = "#doctorId")
    public DoctorDto getDoctor(int doctorId) {
        logger.info("getting from database");
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor", doctorId));
        return DoctorMapper.mapToDoctorDto(doctor);
    }

    @Override
    @CacheEvict(key = "#doctorId")
    public boolean deleteDoctor(int doctorId) {
        logger.info("deleting from cache");
        doctorRepository.findById(doctorId).orElseThrow(() -> new ResourceNotFoundException("Doctor", doctorId));
        doctorRepository.deleteById(doctorId);
        return true;
    }
}
