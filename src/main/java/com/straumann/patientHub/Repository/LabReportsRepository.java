package com.straumann.patientHub.Repository;

import com.straumann.patientHub.entity.LabReports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LabReportsRepository extends JpaRepository<LabReports, Integer> {
}
