package com.straumann.patientHub.mapper;

import com.straumann.patientHub.dto.LabReportsDto;
import com.straumann.patientHub.entity.LabReports;
import com.straumann.patientHub.entity.Patient;

public class LabReportsMapper {

    public static LabReports mapToLabReports(LabReportsDto labReportsDto, Patient patient){

        return LabReports.builder().labReportId(labReportsDto.getLabReportId())
                .patient(patient)
                .date(labReportsDto.getDate())
                .testName(labReportsDto.getTestName())
                .testResult(labReportsDto.getTestResult()).build();

    }

    public static LabReportsDto mapToLabReportsDto(LabReports labReports){
        return LabReportsDto.builder().labReportId(labReports.getLabReportId())
                .patientId(labReports.getPatient().getPatientId())
                .date(labReports.getDate())
                .testName(labReports.getTestName())
                .testResult(labReports.getTestResult()).build();
    }
}
