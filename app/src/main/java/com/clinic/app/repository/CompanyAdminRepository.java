package com.clinic.app.repository;

import com.clinic.app.model.CompanyAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyAdminRepository extends JpaRepository<CompanyAdmin,Long> {
    Optional<CompanyAdmin> findByEmail(String email);
}
