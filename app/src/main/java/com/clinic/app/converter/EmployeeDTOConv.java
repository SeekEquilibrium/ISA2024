package com.clinic.app.converter;

import com.clinic.app.dto.EditEmployeeDTO;
import com.clinic.app.dto.EmployeeDTO;
import com.clinic.app.model.Adress;
import com.clinic.app.model.Employee;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeDTOConv {
    private final EnumConverter  enumConverter;
    private final PasswordEncoder passwordConverter;


    public EmployeeDTOConv(EnumConverter enumConverter, PasswordEncoder passwordConverter) {
        this.enumConverter = enumConverter;
        this.passwordConverter = passwordConverter;
    }

    public EmployeeDTO employeeToDTO(Employee employee){
        return EmployeeDTO.builder()
                .email(employee.getEmail())
                .password(employee.getPassword())
                .name(employee.getName())
                .surname(employee.getSurname())
                .phoneNumber(employee.getPhoneNumber())
                .gender(employee.getGender().toString())
                .occupation(employee.getOccupation())
                .companyInfo(employee.getCompanyInfo())
                .street(employee.getAdress().getStreet())
                .city(employee.getAdress().getCity())
                .country(employee.getAdress().getCountry())
                .build();

    }

    public Employee DTOToEmployee(EmployeeDTO employeeDTO){
        return Employee.builder()
                .email(employeeDTO.getEmail())
                .password(passwordConverter.encode(employeeDTO.getPassword()))
                .name(employeeDTO.getName())
                .surname(employeeDTO.getSurname())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .gender(enumConverter.stringToGender(employeeDTO.getGender()))
                .occupation(employeeDTO.getOccupation())
                .companyInfo(employeeDTO.getCompanyInfo())
                .adress(new Adress(employeeDTO.getStreet(), employeeDTO.getCity(),employeeDTO.getCountry()))
                .build();

    }

    public EditEmployeeDTO editEmployeeToDTO(Employee employee){
        return EditEmployeeDTO.builder()
                .name(employee.getName())
                .surname(employee.getSurname())
                .email(employee.getEmail())
                .phoneNumber(employee.getPhoneNumber())
                .occupation(employee.getOccupation())
                .companyInfo(employee.getCompanyInfo())
                .street(employee.getAdress().getStreet())
                .city(employee.getAdress().getCity())
                .country(employee.getAdress().getCountry())
                .build();

    }

    public Employee DTOToEditEmployee(EditEmployeeDTO employeeDTO){
        return Employee.builder()
                .name(employeeDTO.getName())
                .surname(employeeDTO.getSurname())
                .email(employeeDTO.getEmail())
                .phoneNumber(employeeDTO.getPhoneNumber())
                .occupation(employeeDTO.getOccupation())
                .companyInfo(employeeDTO.getCompanyInfo())
                .adress(new Adress(employeeDTO.getStreet(), employeeDTO.getCity(),employeeDTO.getCountry()))
                .build();

    }


}
