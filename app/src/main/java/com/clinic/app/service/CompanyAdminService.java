package com.clinic.app.service;

import com.clinic.app.model.CompanyAdmin;
import com.clinic.app.repository.CompanyAdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyAdminService {
    private final CompanyAdminRepository companyAdminRepository;

    public CompanyAdminService(CompanyAdminRepository companyAdminRepository){
        this.companyAdminRepository = companyAdminRepository;
    }

    public List<CompanyAdmin> getAll(){
      return  companyAdminRepository.findAll();
    }
    public Optional<CompanyAdmin> findByEmail(String email){
        return companyAdminRepository.findByEmail(email);
    }

    public void register (CompanyAdmin companyAdmin) {
        companyAdminRepository.save(companyAdmin);
    }
}
