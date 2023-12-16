package com.clinic.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class Employee  extends UserApp{
    @Embedded
    private  Adress adress;
    @Column
    private String occupation;
    @Column
    private String companyInfo;

    @Builder

    public Employee(UUID id, String email, String password, String name, String surname, String phoneNumber, Gender gender, boolean isVerified, Adress adress, String occupation, String companyInfo) {
        super(id, email, password, name, surname, phoneNumber, gender, isVerified);
        this.adress = adress;
        this.occupation = occupation;
        this.companyInfo = companyInfo;
    }
}
