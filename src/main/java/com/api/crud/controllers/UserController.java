package com.api.crud.controllers;

import com.api.crud.models.UserModels;
import com.api.crud.repositories.dto.ResponsableResponse;
import com.api.crud.repositories.dto.UserResponse;
import com.api.crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping(path = "findallusers")
    public List<ResponsableResponse> getUsers() {
        return this.userService.getResponsables();
    }

    @PostMapping
    public UserModels saveUser(@RequestBody UserModels user) {
        return this.userService.saveUser(user);
    }

    @GetMapping(path = "/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        return this.userService.getByid(id);
    }

    @PutMapping(path = "/{id}")
    public UserModels updateUserById(@RequestBody UserModels request, @PathVariable Long id) {
        return this.userService.updateById(request, id);
    }


} 
