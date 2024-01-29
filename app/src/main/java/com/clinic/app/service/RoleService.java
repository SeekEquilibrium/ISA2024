package com.clinic.app.service;

import com.clinic.app.model.Role;
import com.clinic.app.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService  {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public Role findById(Long id) {
        Optional<Role> role = repository.findById(id);
        return role.orElse(null);
    }

    public Role findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }
}
