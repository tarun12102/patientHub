package com.straumann.patientHub.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor {
    @Id
    int doctorId;
    String name;
    String email;
    String mobileNumber;
    String address;
    int age;
    String gender;
    String speciality;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    List<Appointment> appointments;
}
