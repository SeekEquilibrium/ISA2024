package com.clinic.app.config;

import com.clinic.app.model.Role;
import com.clinic.app.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class PostConstructConfig {
    private final RoleRepository roleRepository;

    public PostConstructConfig(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }

    @PostConstruct
    public void create(){
        if(roleRepository.findAll().isEmpty()){
            Role roleAdmin = new Role("ROLE_EMPLOYEE");
            Role roleDonor = new Role("ROLE_ADMIN");
            Role roleStaff = new Role("ROLE_SYSADMIN");
            roleRepository.save(roleAdmin);
            roleRepository.save(roleDonor);
            roleRepository.save(roleStaff);
        }
    }
}
