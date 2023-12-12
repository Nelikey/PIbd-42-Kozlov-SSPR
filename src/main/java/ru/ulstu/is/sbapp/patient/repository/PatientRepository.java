package ru.ulstu.is.sbapp.patient.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.ulstu.is.sbapp.patient.model.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
}
