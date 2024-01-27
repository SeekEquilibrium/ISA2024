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

    public Employee(long id, String email, String password, String name, String surname, String phoneNumber, Gender gender, boolean isVerified ,Role role, Adress adress, String occupation, String companyInfo) {
        super(id, email, password, name, surname, phoneNumber, gender, isVerified, role);
        this.adress = adress;
        this.occupation = occupation;
        this.companyInfo = companyInfo;
    }
}
