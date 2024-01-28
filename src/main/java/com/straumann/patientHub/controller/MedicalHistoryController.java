package com.straumann.patientHub.controller;


import com.straumann.patientHub.dto.MedicalHistoryDto;
import com.straumann.patientHub.entity.MedicalHistory;
import com.straumann.patientHub.service.MedicalHistoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicalHistory")
public class MedicalHistoryController {

    @Autowired
    MedicalHistoryService medicalHistoryService;

    @PostMapping("/create")
    public ResponseEntity<MedicalHistoryDto> createMedicalHistory(@RequestBody @Valid MedicalHistoryDto medicalHistoryDto) {

        MedicalHistoryDto medicalHistoryDto1 = medicalHistoryService.createMedicalHistory(medicalHistoryDto);
        return  new ResponseEntity<>(medicalHistoryDto1, HttpStatus.CREATED);
    }


    @PutMapping("/update")
    public ResponseEntity<MedicalHistoryDto> updateMedicalHistory(@RequestBody @Valid MedicalHistoryDto medicalHistoryDto){
        MedicalHistoryDto updatedMedicalHistory = medicalHistoryService.updateMedicalHistory(medicalHistoryDto);
        return new ResponseEntity<>(updatedMedicalHistory,HttpStatus.OK);
    }

    @GetMapping("/get/{medicalHistoryId}")
    public ResponseEntity<MedicalHistoryDto> getMedicalHistory(@PathVariable int medicalHistoryId){
        MedicalHistoryDto medicalHistoryDto = medicalHistoryService.getMedicalHistory(medicalHistoryId);
        return new ResponseEntity<>(medicalHistoryDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{medicalHistoryId}")
    public ResponseEntity<String> deleteMedicalHistory(@PathVariable int medicalHistoryId){
        boolean deleted = medicalHistoryService.deleteMedicalHistory(medicalHistoryId);
        if(deleted)
            return new ResponseEntity<>("Successfully deleted the medical History , id : "+medicalHistoryId,HttpStatus.OK);
        return new ResponseEntity<>("Not able to delete the medical History , id : "+medicalHistoryId,HttpStatus.OK);
    }
}
