package com.example.ToDo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ToDo.dto.LoginDTO;
import com.example.ToDo.dto.TokenDTO;
import com.example.ToDo.dto.UserDTO;
import com.example.ToDo.dto.UserDTOEdit;
import com.example.ToDo.security.JwtTokenUtil;
import com.example.ToDo.security.JwtUserDetailsService;
import com.example.ToDo.service.UserService;

@RestController
@RequestMapping("auth")
@Validated
public class UserController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	@PostMapping("register")
	public ResponseEntity<UserDTO> register(@Valid @RequestBody UserDTO userDTO){
		return new ResponseEntity<>(userService.add(userDTO), HttpStatus.CREATED);
	}
	
	@PostMapping("login")
	public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO loginDTO) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
		

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(loginDTO.getEmail());

		final String token = jwtTokenUtil.generateToken(userDetails);

		return ResponseEntity.ok(new TokenDTO(token));
	}
	
	@PutMapping("edit")
	public ResponseEntity<UserDTO>edit(@RequestBody UserDTOEdit userDTOEdit){
		return new ResponseEntity<>(userService.edit(userDTOEdit), HttpStatus.OK);
		
	}

}
