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
public class Patient {
    @Id
    int patientId;
    String name;
    String email;
    String mobileNumber;
    String address;
    int age;
    String gender;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    List<MedicalHistory> medicalHistories;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    List<Appointment> appointments;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    List<LabReports> labReports;
}
