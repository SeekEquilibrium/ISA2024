package com.clinic.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="medicalequipment")
public class MedicalEquipment {
    @Id
    @SequenceGenerator(name = "medicalEquipmentSeqGen", sequenceName = "medicalEquipment", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "medicalEquipmentSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private long id;
    @Column
    private String name;
    @Column
    private double amount;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Builder
    public MedicalEquipment(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }
}
