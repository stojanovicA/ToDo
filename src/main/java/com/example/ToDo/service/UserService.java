package com.example.ToDo.service;

import java.util.Optional;

import com.example.ToDo.dto.TaskDTO;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.ToDo.dto.UserDTO;
import com.example.ToDo.dto.UserDTOEdit;
import com.example.ToDo.entity.UserEntity;

public interface UserService extends CrudService<UserDTO,Integer>  {
	
	UserDTO findByEmail(String email);

	public UserDTO edit(UserDTOEdit userDTOEdit);

}
