package com.straumann.patientHub.Repository;

import com.straumann.patientHub.entity.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory,Integer> {
}
