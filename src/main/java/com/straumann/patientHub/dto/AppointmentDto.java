package com.straumann.patientHub.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {

    @NotNull(message = "appointmentId can not be a empty")
    int appointmentId;

    @NotNull(message = "patientId can not be a empty")
    int patientId;

    @NotNull(message = "doctorId can not be a empty")
    int doctorId;

    @JsonFormat(pattern="yyyy-MM-dd")
    Date dateOfAppointment;
}
