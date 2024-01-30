package com.clinic.app.service;

import com.clinic.app.model.SystemAdmin;
import com.clinic.app.repository.SystemAdminRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SystemAdminService {
    private final SystemAdminRepository systemAdminRepository;

    public SystemAdminService (SystemAdminRepository systemAdminRepository){
        this.systemAdminRepository= systemAdminRepository;
    }

    public Optional<SystemAdmin> findByEmail(String email) {
        return  systemAdminRepository.findByEmail(email);
    }
}
