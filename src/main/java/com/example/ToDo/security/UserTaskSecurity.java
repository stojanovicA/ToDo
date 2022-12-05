package com.example.ToDo.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.example.ToDo.entity.Task;
import com.example.ToDo.repository.TaskRepository;


@Component("userTaskSecurity")
public class UserTaskSecurity {
	
	@Autowired
	TaskRepository taskRepository;
	
	public boolean isOwnerOfTask(Authentication authentication, int id) {
		Optional<Task> taskOptional =  taskRepository.findById(id);
		if(taskOptional.isEmpty()) {
			return false;
		}
		Task task = taskRepository.findById(id).get();
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		
		
		return task.getUser().getEmail().equals(userDetails.getUsername());
	}
 // 	$2a$10$yM61t6ac/KmvrPg/im/Pz.66iTsxmNSzaST1mNLmTGES//lkQJp1G
	//  $2a$10$z3dP24Xin0.auG15eUEBqOukiVAfEx424tVbU/1vPHoyuwGOsNtTm
	//
}
