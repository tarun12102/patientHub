package com.straumann.patientHub.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PatientDto {


    @NotNull(message = "patientId can not be a empty")
    int patientId;
    @NotEmpty(message = "name can not be a empty")
    String name;
    String email;
    String mobileNumber;
    String address;
    int age;
    String gender;
}
