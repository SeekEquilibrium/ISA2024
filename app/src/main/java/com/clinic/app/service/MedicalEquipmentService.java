package com.clinic.app.service;

import com.clinic.app.model.MedicalEquipment;
import com.clinic.app.repository.MedicalEquipmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalEquipmentService {
    private final MedicalEquipmentRepository medicalEquipmentRepository;

    public MedicalEquipmentService(MedicalEquipmentRepository medicalEquipmentRepository) {
        this.medicalEquipmentRepository = medicalEquipmentRepository;
    }
    public List<MedicalEquipment> getAll() {
        return medicalEquipmentRepository.findAll();
    }
    public MedicalEquipment getById(Long id) {return  medicalEquipmentRepository.findAllById(id);}
    public MedicalEquipment update(MedicalEquipment equipment) {
        MedicalEquipment fromRepo = getById(equipment.getId());
        medicalEquipmentRepository.delete(fromRepo);
        if(fromRepo == null){
            return null;
        }
        MedicalEquipment swapped = swapValues(fromRepo, equipment);

        medicalEquipmentRepository.delete(fromRepo);
        return medicalEquipmentRepository.save(swapped);
    }
    private MedicalEquipment swapValues(MedicalEquipment fromRepo, MedicalEquipment equipment){
        fromRepo.setAmount(equipment.getAmount());
        fromRepo.setId(equipment.getId());
        return fromRepo;
    }
}
