package com.straumann.patientHub.controller;


import com.straumann.patientHub.dto.LabReportsDto;
import com.straumann.patientHub.service.LabReportsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/labReports")
public class LabReportsController {

    @Autowired
    LabReportsService labReportsService;

    @PostMapping("/create")
    public ResponseEntity<LabReportsDto> createLabReports(@RequestBody @Valid LabReportsDto labReportsDto){
        LabReportsDto createdLabReport = labReportsService.createLabReport(labReportsDto);
        return new ResponseEntity<>(createdLabReport, HttpStatus.OK);
    }

    @GetMapping("/get/{labReportId}")
    public ResponseEntity<LabReportsDto> getLabReport(@PathVariable int labReportId){
        LabReportsDto labReportsDto = labReportsService.getLabReport(labReportId);
        return new ResponseEntity<>(labReportsDto,HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<LabReportsDto> updateLabReports(@RequestBody @Valid LabReportsDto labReportsDto){
        LabReportsDto updatedLabReports = labReportsService.updateLabReport(labReportsDto);
        return new ResponseEntity<>(updatedLabReports,HttpStatus.OK);
    }



    @DeleteMapping("/delete/{labReportId}")
    public ResponseEntity<String> deleteLabReports(@PathVariable int labReportId){
        boolean deleted = labReportsService.deleteLabReport(labReportId);

        if(deleted)
            return new ResponseEntity<>("successfully deleted the lab report of id "+labReportId,HttpStatus.OK);
        return new ResponseEntity<>("Not able to delete the lab report of id : "+labReportId,HttpStatus.OK);
    }
}
