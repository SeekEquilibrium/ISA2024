package com.clinic.app.controller;

import com.clinic.app.converter.EmployeeDTOConv;
import com.clinic.app.dto.EditEmployeeDTO;
import com.clinic.app.dto.EmployeeDTO;
import com.clinic.app.model.Employee;
import com.clinic.app.service.EmployeeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value="/employee")
@Api(value="/employee",tags="Employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final EmployeeDTOConv employeeDTOConv;

    @Autowired
    public  EmployeeController(EmployeeService employeeService, EmployeeDTOConv employeeDTOConv){
        this.employeeService = employeeService;
        this.employeeDTOConv = employeeDTOConv;
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAll(){
        List<Employee> employees = employeeService.getAll();
        List<EmployeeDTO> employeeDTOS = employees.stream().map(employeeDTOConv::employeeToDTO).toList();
        return ResponseEntity.ok(employeeDTOS);
    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<EmployeeDTO> getCurrentEmployee(Principal principal){
        Employee employee = employeeService.getEmail(principal.getName());
        if(employee==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(employeeDTOConv.employeeToDTO(employee));
        }
    }
    @PutMapping(value = "/edit")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public ResponseEntity<EmployeeDTO> edit(@RequestBody EditEmployeeDTO employeeDTO){
        Employee employee = employeeService.edit(employeeDTOConv.DTOToEditEmployee(employeeDTO));
        if(employee==null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(employeeDTOConv.employeeToDTO(employee));
        }
    }
}
