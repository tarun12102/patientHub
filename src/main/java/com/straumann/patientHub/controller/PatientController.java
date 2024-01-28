package com.straumann.patientHub.controller;


import com.straumann.patientHub.dto.PatientDto;
import com.straumann.patientHub.service.PatientService;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    PatientService patientService;


    @PostMapping("/create")
    public ResponseEntity<PatientDto> createPatient(@RequestBody @Valid PatientDto patientDto){
        PatientDto createdPatient = patientService.createPatient(patientDto);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }

    @GetMapping("/get/{patientId}")
    public ResponseEntity<PatientDto> getPatient(@PathVariable int patientId){
        PatientDto patient = patientService.getPatient(patientId);
        return new ResponseEntity<>(patient,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PatientDto> updatePatient(@RequestBody @Valid PatientDto patientDto){
        PatientDto updatedPatient = patientService.updatePatient(patientDto);
        return new ResponseEntity<>(updatedPatient,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{patientId}")
    public ResponseEntity<String> deletePatient(@PathVariable int patientId){
        boolean deleted = patientService.deletePatient(patientId);
        String message;
        if(deleted)
            message = "successfully deleted the patient  id : "+patientId;
        else
            message = "Not able to delete the patient id : "+patientId;

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
