package com.example.doctor_service.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String specialty;
    private String phone;
    private String email;
    private List<WorkingSlotDTO> workingSlots;
}