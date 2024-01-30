package com.clinic.app.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.swagger.annotations.Contact;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company_admins")
public class CompanyAdmin extends UserApp {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Company company;

    @Builder
    public CompanyAdmin(long id, String email, String password, String name, String surname, String phoneNumber, Gender gender, boolean isVerified, Role role, Company company) {
        super(id, email, password, name, surname, phoneNumber, gender, isVerified, role);
        this.company = company;
    }

}