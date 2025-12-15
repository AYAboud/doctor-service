package com.example.doctor_service.service;

import com.example.doctor_service.entity.Doctor;
import com.example.doctor_service.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    @Transactional(readOnly = true)
    public List<Doctor> getAll() {
        return doctorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Doctor getById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Doctor> getBySpecialty(String specialty) {
        return doctorRepository.findBySpecialty(specialty);
    }

    @Transactional
    public Doctor save(Doctor doctor) {
        if (doctor.getEmail() != null && doctorRepository.existsByEmail(doctor.getEmail())) {
            throw new RuntimeException("Doctor with email " + doctor.getEmail() + " already exists");
        }
        return doctorRepository.save(doctor);
    }

    @Transactional
    public Doctor update(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        existingDoctor.setFirstName(doctor.getFirstName());
        existingDoctor.setLastName(doctor.getLastName());
        existingDoctor.setSpecialty(doctor.getSpecialty());
        existingDoctor.setPhone(doctor.getPhone());
        existingDoctor.setEmail(doctor.getEmail());

        return doctorRepository.save(existingDoctor);
    }

    @Transactional
    public void delete(Long id) {
        if (!doctorRepository.existsById(id)) {
            throw new RuntimeException("Doctor not found with id: " + id);
        }
        doctorRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean isDoctorAvailable(Long doctorId, String dayOfWeek, LocalTime time) {
        Doctor doctor = doctorRepository.findByIdWithWorkingSlots(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + doctorId));

        return doctor.getWorkingSlots().stream()
                .anyMatch(slot -> slot.getDayOfWeek().equalsIgnoreCase(dayOfWeek)
                        && !time.isBefore(slot.getStartTime())
                        && !time.isAfter(slot.getEndTime()));
    }

}