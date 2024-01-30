package com.clinic.app.converter;

import com.clinic.app.dto.CompanyAdminDTO;
import com.clinic.app.dto.SystemAdminDTO;
import com.clinic.app.model.CompanyAdmin;
import com.clinic.app.model.SystemAdmin;
import com.clinic.app.service.CompanyAdminService;
import com.clinic.app.service.CompanyService;
import org.springframework.stereotype.Service;

@Service
public class CompanyAdminConverter {
    private final CompanyService companyService;
    private final  EnumConverter enumConverter;


    public CompanyAdminConverter(CompanyService companyService, EnumConverter enumConverter) {
        this.companyService = companyService;
        this.enumConverter = enumConverter;
    }


    public CompanyAdminDTO entityToDto(CompanyAdmin companyAdmin) {
        return CompanyAdminDTO.builder()
                .email(companyAdmin.getEmail())
                .password(companyAdmin.getPassword())
                .gender(companyAdmin.getGender().toString())
                .name(companyAdmin.getName())
                .surname(companyAdmin.getSurname())
                .phoneNumber(companyAdmin.getPhoneNumber())
                .companyId(companyAdmin.getCompany().getId())
                .build();
    }

    public CompanyAdmin dtoToEntity(CompanyAdminDTO adminDto) {
        return CompanyAdmin.builder()
                .email(adminDto.getEmail())
                .password(adminDto.getPassword())
                .name(adminDto.getName())
                .surname(adminDto.getSurname())
                .phoneNumber(adminDto.getPhoneNumber())
                .gender(enumConverter.stringToGender(adminDto.getGender()))
                .company(companyService.getById(Double.valueOf(adminDto.getCompanyId()).longValue()))
                .build();
    }
}
