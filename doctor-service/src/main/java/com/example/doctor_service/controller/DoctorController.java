package com.example.doctor_service.controller;

import com.example.doctor_service.entity.Doctor;
import com.example.doctor_service.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/doctors")
public class DoctorController {

    private final DoctorService service;

    @GetMapping
    public List<Doctor> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Doctor getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/specialty/{specialty}")
    public List<Doctor> getBySpecialty(@PathVariable String specialty) {
        return service.getBySpecialty(specialty);
    }

    @PostMapping
    public Doctor save(@RequestBody Doctor doctor) {
        return service.save(doctor);
    }

    @PutMapping("/{id}")
    public Doctor update(@PathVariable Long id, @RequestBody Doctor doctor) {
        return service.update(id, doctor);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/availability")
    public boolean checkAvailability(
            @PathVariable Long id,
            @RequestParam String dayOfWeek,
            @RequestParam String time) {
        return service.isDoctorAvailable(id, dayOfWeek, java.time.LocalTime.parse(time));
    }


}