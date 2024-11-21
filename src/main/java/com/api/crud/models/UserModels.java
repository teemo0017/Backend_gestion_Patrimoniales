package com.api.crud.models;

//import static org.assertj.core.api.Assertions.offset;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USER_AUTH", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class UserModels implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private int age;

    @Column
    private String password;

    @Basic
    @Column(nullable = false)
    private String username;

    @Enumerated(EnumType.STRING)
    Role role;

    @Column
    private String country;

    // Relación inversa con Bienes donde el usuario es responsable
    @OneToMany(mappedBy = "responsable", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BienesModel> bienesComoResponsable = new ArrayList<>();

    // Relación inversa con Bienes donde el usuario es el creador
    @OneToMany(mappedBy = "usuarioRegistro", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BienesModel> bienesCreados = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority((role.name())));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
