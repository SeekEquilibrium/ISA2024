package com.clinic.app.service;

import com.clinic.app.model.Employee;
import com.clinic.app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public List<Employee> getAll(){
        return  employeeRepository.findAll();
    }
    public Employee getEmail(String email){
        return employeeRepository.findByEmail(email).orElse(null);
    }

    public Employee edit(Employee employee){
        Employee from = employeeRepository.findById(employee.getId()).orElse(null);
        if(from == null){
            return null;
        } else {
            from.setName(employee.getName());
            from.setSurname(employee.getSurname());
            from.setEmail(employee.getEmail());
            from.setPassword(employee.getPassword());
            from.setPhoneNumber(employee.getPhoneNumber());
            from.setGender(employee.getGender());
            from.setOccupation(employee.getOccupation());
            from.setAdress(employee.getAdress());
            from.setCompanyInfo(employee.getCompanyInfo());

            return employeeRepository.save(from);
        }
    }
}
