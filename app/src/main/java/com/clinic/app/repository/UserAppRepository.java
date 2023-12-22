package com.clinic.app.repository;

import com.clinic.app.model.UserApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserAppRepository extends JpaRepository<UserApp, Long> {
    UserApp findByEmail(String username);

}
