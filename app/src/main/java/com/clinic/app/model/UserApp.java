package com.clinic.app.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserApp implements UserDetails {

    @Id
    @SequenceGenerator(name = "userAppSeqGen", sequenceName = "userAppSeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "userAppSeqGen")
    @Column(name="id", unique=true, nullable=false)
    private  long id;
    @Column(unique = true)
    private String email;
    @Column
    private String password;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String phoneNumber;
    @Column
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    @Column
    private boolean isVerified;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public UserApp(long id, String email, String password, String name, String surname, String phoneNumber, Gender gender, boolean isVerified, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.isVerified = isVerified;
        this.role = role;
    }

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<Role> roles = new ArrayList<>();
        roles.add(this.role);
        return roles;
    }


    @Override
    public String getUsername() {
        return this.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return isVerified;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isVerified;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isVerified;
    }

    @Override
    public boolean isEnabled() {
        return isVerified;
    }



}
