package com.example.doctor_service.controller;

import com.example.doctor_service.dto.PatientDTO;
import com.example.doctor_service.entity.Consultation;
import com.example.doctor_service.service.ConsultationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/consultations")
public class ConsultationController {

    private final ConsultationService service;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Consultation>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getById(@PathVariable Long id) {
        Consultation c = service.getById(id);
        if (c == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(c);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Consultation> save(@RequestBody Consultation c) {
        return ResponseEntity.ok(service.save(c));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Consultation> update(@PathVariable Long id, @RequestBody Consultation c) {
        Consultation updated = service.update(id, c);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // GET PATIENT OF A CONSULTATION
    @GetMapping("/{id}/patient")
    public ResponseEntity<PatientDTO> getPatientOfConsultation(@PathVariable Long id) {
        PatientDTO patient = service.getPatientOfConsultation(id);
        if (patient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(patient);
    }
}
