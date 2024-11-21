package com.api.crud.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.crud.models.UserModels;

@Repository
public interface IUserRepository extends JpaRepository<UserModels, Long> {
	
Optional<UserModels> findByUsername(String username);

}
