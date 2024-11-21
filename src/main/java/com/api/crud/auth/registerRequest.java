package com.api.crud.auth;

import com.api.crud.models.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class registerRequest {
String username;
String password;
String firstname;
String lastname;
String country;
String email;
String role;
int age;
String phone;

}
