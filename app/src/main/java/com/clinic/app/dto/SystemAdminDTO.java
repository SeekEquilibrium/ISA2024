package com.clinic.app.dto;

import com.clinic.app.model.SystemAdmin;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SystemAdminDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String gender;
    private boolean isVerified;

@Builder
    public SystemAdminDTO(String email, boolean isVerified,String password, String name, String surname, String phoneNumber, String gender) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.isVerified = isVerified;
    }

    public SystemAdminDTO(SystemAdmin admin){
        this.email = admin.getEmail();
        this.password = admin.getPassword();
        this.name = admin.getName();
        this.surname = admin.getSurname();
        this.phoneNumber = admin.getPhoneNumber();
        this.gender = admin.getGender().toString();
        this.isVerified=admin.isVerified();
    }
}
