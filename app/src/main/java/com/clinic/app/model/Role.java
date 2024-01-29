package com.clinic.app.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name="ROLE")
@NoArgsConstructor
public class Role implements GrantedAuthority {

    @Id
    @SequenceGenerator(name = "RoleSeqGen", sequenceName = "RoleSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "RoleSeqGen")
    Long id;
    @Column(name="name")
    String name;
    @Override
    public String getAuthority() {
        return this.name;
    }
    public Role(String name) {
        this.name = name;
    }
}
