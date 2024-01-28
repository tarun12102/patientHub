package com.straumann.patientHub.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {


    @Id
    int appointmentId;

    @ManyToOne
    Patient patient;

    @ManyToOne
    Doctor doctor;

    @OneToOne(mappedBy = "appointment",cascade = CascadeType.ALL)
    Prescription prescription;

    Date dateOfAppointment;
}
