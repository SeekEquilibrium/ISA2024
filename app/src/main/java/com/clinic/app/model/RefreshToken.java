package com.clinic.app.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="refresh_tokens")
@Entity
@Builder
public class RefreshToken {
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserApp user;

    @Id
    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    private Long expiryDate;
}
