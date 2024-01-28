package com.straumann.patientHub.Repository;

import com.straumann.patientHub.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {
}
