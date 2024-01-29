package com.clinic.app.service;

import com.clinic.app.dto.EditEmployeeDTO;
import com.clinic.app.dto.EmployeeDTO;
import com.clinic.app.model.Employee;
import com.clinic.app.model.Gender;
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
        Employee from = employeeRepository.findByEmail(employee.getEmail()).orElse(null);
        if(from == null){
            return null;
        } else {
            Employee swapped = swapValue(from,employee);

            return employeeRepository.save(swapped);
        }
    }

    private Employee swapValue(Employee from , Employee employee){
        from.setName(employee.getName());
        from.setSurname(employee.getSurname());
        from.setPhoneNumber(employee.getPhoneNumber());
        from.setOccupation(employee.getOccupation());
        from.setAdress(employee.getAdress());
        from.setCompanyInfo(employee.getCompanyInfo());

        return  from;
    }
}
