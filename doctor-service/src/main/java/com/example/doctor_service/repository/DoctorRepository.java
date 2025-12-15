package com.example.doctor_service.repository;

import com.example.doctor_service.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Optional<Doctor> findByEmail(String email);

    List<Doctor> findBySpecialty(String specialty);

    @Query("SELECT DISTINCT d FROM Doctor d LEFT JOIN FETCH d.workingSlots WHERE d.id = :id")
    Optional<Doctor> findByIdWithWorkingSlots(@Param("id") Long id);

    @Query("SELECT DISTINCT d FROM Doctor d LEFT JOIN FETCH d.consultations WHERE d.id = :id")
    Optional<Doctor> findByIdWithConsultations(@Param("id") Long id);

    boolean existsByEmail(String email);
}