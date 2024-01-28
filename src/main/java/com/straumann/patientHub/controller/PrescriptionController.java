package com.straumann.patientHub.controller;


import com.straumann.patientHub.dto.PrescriptionDto;
import com.straumann.patientHub.service.PrescriptionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {


    @Autowired
    PrescriptionService prescriptionService;

    @PostMapping("/create")
    public ResponseEntity<PrescriptionDto> createPrescription(@RequestBody @Valid PrescriptionDto prescriptionDto)
    {
        PrescriptionDto createdPrescription = prescriptionService.createPrescription(prescriptionDto);
        return new ResponseEntity<>(createdPrescription, HttpStatus.CREATED);
    }


    @GetMapping("/get/{prescriptionId}")
    public ResponseEntity<PrescriptionDto> getPrescription(@PathVariable int prescriptionId)
    {
        PrescriptionDto prescriptionDto = prescriptionService.getPrescription(prescriptionId);
        return new ResponseEntity<>(prescriptionDto,HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<PrescriptionDto> updatePrescription(@RequestBody @Valid PrescriptionDto prescriptionDto){
        PrescriptionDto updatedPrescription = prescriptionService.updatePrescription(prescriptionDto);
        return new ResponseEntity<>(updatedPrescription,HttpStatus.OK);
    }


    @DeleteMapping("/delete/{prescriptionId}")
    public ResponseEntity<String> deletePrescription(@PathVariable int prescriptionId)
    {
        boolean deleted = prescriptionService.deletePrescription(prescriptionId);
        if(deleted)
            return new ResponseEntity<>("successfully deleted the prescription  id :"+prescriptionId,HttpStatus.OK);
        return new ResponseEntity<>("Not able to delete the prescription id : "+prescriptionId,HttpStatus.OK);
    }
}
