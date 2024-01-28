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
public class LabReports {

    @Id
    int labReportId;

    @ManyToOne
    Patient patient;
    Date date;
    String testName;
    String testResult;
}
