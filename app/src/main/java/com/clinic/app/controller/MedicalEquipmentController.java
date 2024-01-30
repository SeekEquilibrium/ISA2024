package com.clinic.app.controller;


import com.clinic.app.converter.MedicalEquipmentConverter;
import com.clinic.app.dto.MedicalEquipmentDTO;
import com.clinic.app.model.MedicalEquipment;
import com.clinic.app.repository.MedicalEquipmentRepository;
import com.clinic.app.service.MedicalEquipmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "/medicalequipment", tags = "Medical equipment")
@RequestMapping(value = "/medicalequipment")
public class MedicalEquipmentController {
    private final MedicalEquipmentRepository medicalEquipmentRepository;
    private final MedicalEquipmentService medicalEquipmentService;
    private final MedicalEquipmentConverter medicalEquipmentConverter;

    @Autowired
    public MedicalEquipmentController(MedicalEquipmentRepository medicalEquipmentRepository, MedicalEquipmentService medicalEquipmentService, MedicalEquipmentConverter medicalEquipmentConverter) {
        this.medicalEquipmentRepository = medicalEquipmentRepository;
        this.medicalEquipmentService = medicalEquipmentService;
        this.medicalEquipmentConverter = medicalEquipmentConverter;
    }

    @GetMapping
    public ResponseEntity<List<MedicalEquipmentDTO>> getAll(){
        List<MedicalEquipment> medicalEquipment = medicalEquipmentService.getAll();
        List<MedicalEquipmentDTO> medicalEquipmentDTOList = medicalEquipment.stream().map(medicalEquipmentConverter::entityToDto).toList();
        return ResponseEntity.ok(medicalEquipmentDTOList);
    }
    @PutMapping(value = "/update")
    @PreAuthorize("hasRole('ROLE_STAFF')")
    public ResponseEntity<MedicalEquipmentDTO> update(@RequestBody MedicalEquipmentDTO medicalEquipmentDTO){
        MedicalEquipment retVal = medicalEquipmentService.update(medicalEquipmentConverter.dtoToEntity(medicalEquipmentDTO));
        return retVal == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(medicalEquipmentConverter.entityToDto(retVal));
    }

}
