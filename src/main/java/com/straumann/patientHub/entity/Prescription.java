package com.straumann.patientHub.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Prescription {

    @Id
    int prescriptionId;

    String testsSuggested;

    String remarks;

    @OneToOne(fetch = FetchType.LAZY)
    Appointment appointment;

}
