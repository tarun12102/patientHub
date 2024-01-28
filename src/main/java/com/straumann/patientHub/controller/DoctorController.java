package com.straumann.patientHub.controller;


import com.straumann.patientHub.dto.DoctorDto;
import com.straumann.patientHub.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.OutputKeys;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;


    @PostMapping("/create")
    public ResponseEntity<DoctorDto> createDoctor(@RequestBody @Valid DoctorDto doctorDto){
        DoctorDto doctorDtoCreated = doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(doctorDtoCreated, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody @Valid DoctorDto doctorDto){
        DoctorDto updatedDoctor = doctorService.updateDoctor(doctorDto);
        return new ResponseEntity<>(updatedDoctor,HttpStatus.OK);
    }


    @GetMapping("/get/{doctorId}")
    public ResponseEntity<DoctorDto> getDoctor(@PathVariable int doctorId){
        DoctorDto doctor = doctorService.getDoctor(doctorId);
        return new ResponseEntity<>(doctor,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{doctorId}")
    public ResponseEntity<String> deleteDoctor(@PathVariable int doctorId){
        boolean deleted = doctorService.deleteDoctor(doctorId);
        String message;
        if(deleted)
            message = "successfully deleted the doctor  id : "+doctorId;
        else
            message = "Not able to delete the doctor id : "+doctorId;

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
