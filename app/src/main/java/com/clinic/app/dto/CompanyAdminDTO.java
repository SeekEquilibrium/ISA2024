package com.clinic.app.dto;

import com.clinic.app.model.CompanyAdmin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CompanyAdminDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String gender;
    private boolean isVerified;
    private double companyId;

    public CompanyAdminDTO(CompanyAdmin admin){
        this.email = admin.getEmail();
        this.password = admin.getPassword();
        this.name = admin.getName();
        this.surname = admin.getSurname();
        this.phoneNumber = admin.getPhoneNumber();
        this.gender = admin.getGender().toString();
        this.isVerified=admin.isVerified();
        this.companyId = admin.getCompany().getId();
    }

}
