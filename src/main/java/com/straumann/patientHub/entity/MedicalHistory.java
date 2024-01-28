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
public class MedicalHistory {

    @Id
    int medicalHistoryId;
    String diseaseName;
    Date fromDate;
    Date toDate;
    String treatmentUsed;

    @ManyToOne
    Patient patient;

}
