package com.example.ToDo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ToDo.entity.UserEntity;

public interface UserRepository  extends JpaRepository<UserEntity, Integer>  {
	
	Optional<UserEntity> findByEmail(String email);
	
	
	
	
	
	
	

}
