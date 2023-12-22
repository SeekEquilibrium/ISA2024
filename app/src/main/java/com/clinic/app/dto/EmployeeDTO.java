package com.clinic.app.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class EmployeeDTO {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String phoneNumber;
    private String gender;
    private String occupation;
    private String companyInfo;
    private String street;
    private String city;
    private String country;

    public EmployeeDTO(String email, String password, String name, String surname, String phoneNumber, String gender, String occupation, String companyInfo, String street, String city, String country) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.occupation = occupation;
        this.companyInfo = companyInfo;
        this.street = street;
        this.city = city;
        this.country = country;
    }
}
