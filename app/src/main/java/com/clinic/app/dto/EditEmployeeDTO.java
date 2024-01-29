package com.clinic.app.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class EditEmployeeDTO {
    private String name;
    private String surname;
    private String phoneNumber;
    private String gender;
    private String occupation;
    private String companyInfo;
    private String street;
    private String city;
    private String country;
    private String email;
    public EditEmployeeDTO(String name,String email, String surname, String phoneNumber, String gender, String occupation, String companyInfo, String street, String city, String country) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.occupation = occupation;
        this.companyInfo = companyInfo;
        this.street = street;
        this.city = city;
        this.country = country;
        this.email = email;
    }
}
