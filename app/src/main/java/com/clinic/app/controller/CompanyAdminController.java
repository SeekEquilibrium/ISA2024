package com.clinic.app.controller;

import com.clinic.app.converter.CompanyAdminConverter;
import com.clinic.app.dto.CompanyAdminDTO;
import com.clinic.app.dto.ListCompanyAdminDTO;
import com.clinic.app.model.CompanyAdmin;
import com.clinic.app.service.CompanyAdminService;
import com.clinic.app.service.RoleService;
import com.clinic.app.service.UserAppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@Api(value ="/company-admin",tags = "Company_Admin")
@RequestMapping(value = "/company_admin")
public class CompanyAdminController {
    private final RoleService roleService;
    private final CompanyAdminService companyAdminService;
    private final CompanyAdminConverter companyAdminConverter;
    private final UserAppService userAppService;

    @Autowired
    public CompanyAdminController(RoleService roleService, CompanyAdminService companyAdminService, CompanyAdminConverter companyAdminConverter, UserAppService userAppService) {
        this.roleService = roleService;
        this.companyAdminService = companyAdminService;
        this.companyAdminConverter = companyAdminConverter;
        this.userAppService = userAppService;
    }

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<CompanyAdmin> companyAdmins = companyAdminService.getAll();
        List<CompanyAdminDTO> companyAdminDTOList = companyAdmins.stream().map(companyAdminConverter::entityToDto).toList();
        return ResponseEntity.ok(new ListCompanyAdminDTO(companyAdminDTOList));
    }
    @GetMapping("/current")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CompanyAdminDTO> getCurrentStaff(Principal principal) {
        Optional<CompanyAdmin> staff = companyAdminService.findByEmail(principal.getName());
        return staff.map(companyAdmin -> ResponseEntity.ok(companyAdminConverter.entityToDto(companyAdmin))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping(value ="/register")
    public ResponseEntity<?> register(@RequestBody CompanyAdminDTO companyAdminDTO){
        CompanyAdmin companyAdmin = companyAdminConverter.dtoToEntity(companyAdminDTO);
        if (userAppService.findbyEmail(companyAdminDTO.getEmail()) == null) {
            companyAdmin.setRole(roleService.findByName("ROLE_ADMIN"));
            companyAdminService.register(companyAdmin);
            return new ResponseEntity<>((companyAdmin), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

}
