package com.example.ToDo.security;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ToDo.dto.UserDTO;
import com.example.ToDo.service.UserService;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	
	@Autowired
	private UserService userService;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO userDTO = userService.findByEmail(username);
		if (userDTO != null) {
			return new User(userDTO.getEmail(), userDTO.getPassword(),
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
}