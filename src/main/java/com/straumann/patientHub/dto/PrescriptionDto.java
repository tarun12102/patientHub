package com.straumann.patientHub.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrescriptionDto {


    @NotNull(message = "prescriptionId can not be a empty")
    int prescriptionId;

    String testsSuggested;

    @NotEmpty(message = "remarks can not be a empty")
    String remarks;

    @NotNull(message = "appointmentId can not be a empty")
    int appointmentId;
}
