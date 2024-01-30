package com.clinic.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
public class CompanyDTO {
    private String name;
    private String street;
    private String city;
    private String country;
    private String description;
    private String workingHoursStart;
    private String workingHoursEnd;

    public CompanyDTO(String name, String street, String city, String country, String description, String workingHoursStart, String workingHoursEnd) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.country = country;
        this.description = description;
        this.workingHoursStart = workingHoursStart;
        this.workingHoursEnd = workingHoursEnd;
    }
}
