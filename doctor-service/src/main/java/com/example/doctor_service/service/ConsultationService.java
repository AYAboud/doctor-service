package com.example.doctor_service.service;

import com.example.doctor_service.dto.PatientDTO;
import com.example.doctor_service.entity.Consultation;
import com.example.doctor_service.repository.ConsultationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultationService {

    private final ConsultationRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public List<Consultation> getAll() {
        return repository.findAll();
    }

    public Consultation save(Consultation c) {
        return repository.save(c);
    }

    public PatientDTO getPatient(Long patientId) {
        return restTemplate.getForObject(
                "http://localhost:8082/patients/" + patientId,
                PatientDTO.class
        );
    }
    public Consultation getById(Long id) {
    return repository.findById(id).orElse(null);
}

public Consultation update(Long id, Consultation updated) {
    Consultation existing = getById(id);
    if (existing == null) return null;

    existing.setDoctorId(updated.getDoctorId());
    existing.setPatientId(updated.getPatientId());
    existing.setDate(updated.getDate());
    existing.setTime(updated.getTime());
    existing.setDescription(updated.getDescription());
    existing.setStatus(updated.getStatus());

    return repository.save(existing);
}

public void delete(Long id) {
    repository.deleteById(id);
}

public PatientDTO getPatientOfConsultation(Long consultationId) {
    Consultation c = getById(consultationId);
    if (c == null) return null;
    return getPatient(c.getPatientId());
}

}
