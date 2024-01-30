package com.clinic.app.controller;

import com.clinic.app.converter.CompanyConverter;
import com.clinic.app.dto.CompanyDTO;
import com.clinic.app.model.Company;
import com.clinic.app.service.CompanyService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Api(value = "companies", tags = "Companies")
@RequestMapping(value = "/company")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyConverter companyConverter;

    public CompanyController(CompanyService companyService, CompanyConverter companyConverter) {
        this.companyService = companyService;
        this.companyConverter = companyConverter;
    }

    @GetMapping
    public ResponseEntity<List<CompanyDTO>> getAll() {
        List<Company> companyList = companyService.getAll();
        List<CompanyDTO> companyDtoList = companyList.stream().map(companyConverter::entityToDto).toList();
        return ResponseEntity.ok(companyDtoList);
    }

    @PostMapping(value = "/register")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    public ResponseEntity registerBank(@RequestBody CompanyDTO companyDTO) {
        Company company = companyConverter.dtoToEntity(companyDTO);
        companyService.register(company);
        return ResponseEntity.ok().build();
    }
}
