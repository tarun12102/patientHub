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
public class MedicalHistoryDto {

    @NotNull(message = "medicalHistoryId can not be a empty")
    int medicalHistoryId;

    @NotEmpty(message = "diseaseName can not be a empty")
    String diseaseName;

    @JsonFormat(pattern="yyyy-MM-dd")
    Date fromDate;

    @JsonFormat(pattern="yyyy-MM-dd")
    Date toDate;

    @NotEmpty(message = "treatmentUsed can not be a empty")
    String treatmentUsed;

    @NotNull(message = "patientId can not be a empty")
    int patientId;
}
