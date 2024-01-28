package com.straumann.patientHub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LabReportsDto {

    @NotNull(message = "labReportId can not be a empty")
    int labReportId;

    @NotNull(message = "patientId can not be a empty")
    int patientId;

    @JsonFormat(pattern="yyyy-MM-dd")
    Date date;

    @NotEmpty(message = "testName can not be a empty")
    String testName;

    @NotEmpty(message = "testResult can not be a empty")
    String testResult;
}
