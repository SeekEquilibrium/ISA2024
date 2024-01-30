package com.clinic.app.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "companies")
public class Company {
    @Id
    @SequenceGenerator(name = "companySeqGen", sequenceName = "companySeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "companySeqGen")
    @Column(name="id", unique=true, nullable=false)
    private  long id;
    @Embedded
    private Adress adress;
    @Embedded
    private TimeInterval workingHours;
    @Column
    private String name;
    @Column
    private String description;
    @JsonBackReference
    @OneToMany(mappedBy = "company" , cascade = CascadeType.ALL)
    private Set<CompanyAdmin> admins;

    @Builder
    public Company(Adress adress,String name,TimeInterval workingHours, String description, Set<CompanyAdmin> admins) {
        this.adress = adress;
        this.name = name;
        this.workingHours = workingHours;
        this.description = description;
        this.admins = admins;
    }
}
