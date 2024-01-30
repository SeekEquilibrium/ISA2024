package com.clinic.app.converter;

import com.clinic.app.dto.MedicalEquipmentDTO;
import com.clinic.app.model.MedicalEquipment;
import org.springframework.stereotype.Service;

@Service
public class MedicalEquipmentConverter {

    public MedicalEquipmentDTO entityToDto(MedicalEquipment medicalEquipment){
        return  MedicalEquipmentDTO.builder()
                .name(medicalEquipment.getName())
                .amount(medicalEquipment.getAmount())
                .build();
    }
    public MedicalEquipment dtoToEntity(MedicalEquipmentDTO medicalEquipmentDTO){
        return MedicalEquipment.builder()
                .name(medicalEquipmentDTO.getName())
                .amount(medicalEquipmentDTO.getAmount())
                .build();
    }
}
