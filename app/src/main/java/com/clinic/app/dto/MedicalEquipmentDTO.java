package com.clinic.app.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MedicalEquipmentDTO {
    private String name;
    private double amount;
}
