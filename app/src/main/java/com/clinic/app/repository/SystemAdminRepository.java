package com.clinic.app.repository;

import com.clinic.app.model.SystemAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SystemAdminRepository extends JpaRepository<SystemAdmin,Long> {
    Optional<SystemAdmin> findByEmail(String email);
}
