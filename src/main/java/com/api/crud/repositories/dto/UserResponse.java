package com.api.crud.repositories.dto;

import com.api.crud.models.Role;
import lombok.Data;

@Data
public class UserResponse {

    private String firstName; // Nombre del usuario

    private String lastName; // Apellido del usuario

    private String email; // Correo electrónico

    private String phone; // Teléfono

    private int age; // Edad

    private String country; // País del usuario

    private Role role;
}