package com.clinic.app.repository;

import com.clinic.app.model.MedicalEquipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalEquipmentRepository extends JpaRepository<MedicalEquipment,Long> {
    MedicalEquipment findAllById(Long id);
}
