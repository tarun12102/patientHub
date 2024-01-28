package com.straumann.patientHub.controller;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.straumann.patientHub.dto.AppointmentDto;
import com.straumann.patientHub.entity.Appointment;
import com.straumann.patientHub.service.AppointmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/create")
    public ResponseEntity<AppointmentDto> createAppointment(@RequestBody @Valid AppointmentDto appointmentDto){

        AppointmentDto appointment = appointmentService.createAppointment(appointmentDto);
        return new ResponseEntity<>(appointment,HttpStatus.CREATED);
    }

    @GetMapping("/get/{appointmentId}")
    public ResponseEntity<AppointmentDto> getAppointment(@PathVariable int appointmentId){

        AppointmentDto appointment = appointmentService.getAppointment(appointmentId);
        return new ResponseEntity<>(appointment,HttpStatus.OK);
    }


    @PutMapping("/updateAppointment")
    public ResponseEntity<AppointmentDto> updateAppointment(@RequestBody @Valid AppointmentDto appointmentDto){

        AppointmentDto appointment = appointmentService.updateAppointment(appointmentDto);
        return new ResponseEntity<>(appointment,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{appointmentId}")
    public ResponseEntity<String> deleteAppointment(@PathVariable int appointmentId){

        boolean deleted = appointmentService.deleteAppointment(appointmentId);
        String message;
        if(deleted)
            message = "successfully deleted the appointment id : "+appointmentId;
        else
            message = "Not able to delete the appointment id : "+appointmentId;

        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
