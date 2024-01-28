package com.straumann.patientHub.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {

    @NotNull(message = "doctorId can not be a empty")
    int doctorId;

    @NotEmpty(message = "name can not be a empty")
    String name;
    String email;
    String mobileNumber;
    String address;
    int age;
    String gender;

    @NotEmpty(message = "speciality can not be a empty")
    String speciality;
}
