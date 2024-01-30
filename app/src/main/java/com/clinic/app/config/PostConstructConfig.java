package com.clinic.app.config;

import com.clinic.app.model.Gender;
import com.clinic.app.model.Role;
import com.clinic.app.model.SystemAdmin;
import com.clinic.app.repository.RoleRepository;
import com.clinic.app.repository.SystemAdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class PostConstructConfig {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final SystemAdminRepository systemAdminRepository;

    public PostConstructConfig(RoleRepository roleRepository , PasswordEncoder passwordEncoder,SystemAdminRepository systemAdminRepository){

        this.roleRepository=roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.systemAdminRepository = systemAdminRepository;
    }

    @PostConstruct
    public void create(){
        if(roleRepository.findAll().isEmpty()){
            Role roleEmployee = new Role("ROLE_EMPLOYEE");
            Role roleAdmin = new Role("ROLE_ADMIN");
            Role roleSystemAdmin = new Role("ROLE_SYSADMIN");
            roleRepository.save(roleEmployee);
            roleRepository.save(roleAdmin);
            roleRepository.save(roleSystemAdmin);


            SystemAdmin systemAdmin = new SystemAdmin(1,"admin@gmail.com",passwordEncoder.encode("Lozinka"),"ime","prezime","123", Gender.MALE,true,roleSystemAdmin);
            systemAdminRepository.save(systemAdmin);
        }

    }
}
