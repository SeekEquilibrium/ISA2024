package com.clinic.app.converter;

import com.clinic.app.dto.SystemAdminDTO;
import com.clinic.app.model.SystemAdmin;
import org.springframework.stereotype.Service;

import javax.persistence.Converter;

@Service
public class SystemAdminConverter{
    private  final EnumConverter enumConverter;

    public SystemAdminConverter(EnumConverter enumConverter){
        this.enumConverter = enumConverter;

    }


    public SystemAdminDTO entityToDto(SystemAdmin admin) {
        return SystemAdminDTO.builder()
                .email(admin.getEmail())
                .password(admin.getPassword())
                .gender(admin.getGender().toString())
                .name(admin.getSurname())
                .surname(admin.getSurname())
                .phoneNumber(admin.getPhoneNumber())
                .build();
    }

    public SystemAdmin dtoToEntity(SystemAdminDTO adminDto) {
        return SystemAdmin.builder()
                .email(adminDto.getEmail())
                .password(adminDto.getPassword())
                .name(adminDto.getName())
                .surname(adminDto.getSurname())
                .phoneNumber(adminDto.getPhoneNumber())
                .gender(enumConverter.stringToGender(adminDto.getGender()))
                .build();
    }

}
