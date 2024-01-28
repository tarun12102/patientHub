package com.straumann.patientHub.service;

import com.straumann.patientHub.dto.LabReportsDto;

public interface LabReportsService {
    LabReportsDto createLabReport(LabReportsDto labReportsDto);

    LabReportsDto getLabReport(int labReportId);

    LabReportsDto updateLabReport(LabReportsDto labReportsDto);

    boolean deleteLabReport(int labReportId);
}
