package com.api.crud.services;

import com.api.crud.models.UserModels;
import com.api.crud.repositories.IUserRepository;
import com.api.crud.repositories.dto.ResponsableResponse;
import com.api.crud.repositories.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    public List<ResponsableResponse> getResponsables() {
        List<ResponsableResponse> responsableResponses = new ArrayList<>();
        List<UserModels> userModels = userRepository.findAll();

        for (UserModels var : userModels) {
            ResponsableResponse responsableResponse = new ResponsableResponse();
            responsableResponse.setId(var.getId());
            responsableResponse.setNombre(var.getFirstName() + " " + var.getLastName());
            responsableResponses.add(responsableResponse);
        }


        return responsableResponses;

    }


    public UserModels saveUser(UserModels user) {
        return userRepository.save(user);
    }

    public UserResponse getByid(Long id) {
        UserModels userEncontrador = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Responsable no encontrado con ID: " + id));

        UserResponse userResponse = new UserResponse();

        userResponse.setAge(userEncontrador.getAge());
        userResponse.setEmail(userEncontrador.getEmail());
        userResponse.setCountry(userEncontrador.getCountry());
        userResponse.setFirstName(userEncontrador.getFirstName());
        userResponse.setLastName(userEncontrador.getLastName());
        userResponse.setPhone(userEncontrador.getPhone());
        return userResponse;
    }

    public UserModels updateById(UserModels request, Long id) {
        UserModels user = userRepository.findById(id).get();

        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());

        userRepository.save(user);

        return user;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }


}
