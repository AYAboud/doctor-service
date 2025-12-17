package com.example.doctor_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.doctor_service.repository.DoctorRepository;

@SpringBootApplication
public class DoctorServiceApplication implements CommandLineRunner {

    @Autowired
    private DoctorRepository doctorRepository;

    public static void main(String[] args) {

        SpringApplication.run(DoctorServiceApplication.class, args);
    }

}
