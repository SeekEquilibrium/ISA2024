package com.clinic.app.converter;

import com.clinic.app.dto.CompanyDTO;
import com.clinic.app.model.Adress;
import com.clinic.app.model.Company;
import com.clinic.app.model.TimeInterval;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class CompanyConverter{


    public CompanyDTO entityToDto(Company company) {
        return CompanyDTO.builder()
                .name(company.getName())
                .street(company.getAdress().getStreet())
                .city(company.getAdress().getCity())
                .country(company.getAdress().getCountry())
                .description(company.getDescription())
                .workingHoursStart(company.getWorkingHours().getIntervalStart().toString())
                .workingHoursEnd(company.getWorkingHours().getIntervalEnd().toString())
                .build();
    }

    public Company dtoToEntity(CompanyDTO bloodBankDto) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            return Company.builder()
                    .name(bloodBankDto.getName())
                    .adress(new Adress(bloodBankDto.getStreet(), bloodBankDto.getCity(), bloodBankDto.getCountry()))
                    .description(bloodBankDto.getDescription())
                    .workingHours(new TimeInterval( format.parse(bloodBankDto.getWorkingHoursStart()), format.parse(bloodBankDto.getWorkingHoursEnd())))
                    .build();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

    }

}