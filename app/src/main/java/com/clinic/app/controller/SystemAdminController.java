package com.clinic.app.controller;

import com.clinic.app.converter.SystemAdminConverter;
import com.clinic.app.dto.SystemAdminDTO;
import com.clinic.app.model.SystemAdmin;
import com.clinic.app.service.RoleService;
import com.clinic.app.service.SystemAdminService;
import com.clinic.app.service.UserAppService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

@RestController
@Api(value = "/system_admin", tags = "System_Admins")
@RequestMapping(value = "/system_admin")
public class SystemAdminController {

    private final SystemAdminService systemAdminService;

    private final SystemAdminConverter converter;

    @Autowired
    public SystemAdminController (SystemAdminService systemAdminService, SystemAdminConverter converter ){

        this.systemAdminService = systemAdminService;
        this.converter = converter;

    }

    @GetMapping("/current")
    @PreAuthorize("hasRole('ROLE_SYSADMIN')")
    public ResponseEntity<SystemAdminDTO> getCurrentAdmin(Principal principal) {
        Optional<SystemAdmin> admin = systemAdminService.findByEmail(principal.getName());
        return admin.map(admin1 -> ResponseEntity.ok(converter.entityToDto(admin1))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
