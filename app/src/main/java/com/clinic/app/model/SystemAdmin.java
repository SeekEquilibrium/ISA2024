package com.clinic.app.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "system_admins")
public class SystemAdmin extends UserApp {
    @Builder
    public SystemAdmin(long id, String email, String password, String name, String surname, String phoneNumber, Gender gender, boolean isVerified, Role role) {
        super(id, email, password, name, surname, phoneNumber, gender, isVerified, role);
    }

}
