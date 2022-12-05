package com.example.ToDo.service.implementation;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ToDo.dto.UserDTO;
import com.example.ToDo.dto.UserDTOEdit;
import com.example.ToDo.dto.converter.UserDTOToUser;
import com.example.ToDo.dto.converter.UserToUserDTO;
import com.example.ToDo.dto.converter.UserToUserDTOEdit;

import com.example.ToDo.entity.UserEntity;
import com.example.ToDo.repository.UserRepository;
import com.example.ToDo.service.UserService;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserToUserDTOEdit userToUserDTOEdit;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	private UserToUserDTO userToUserDTO;

	
	@Autowired
	private UserDTOToUser userDTOToUser;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDTO findById(Integer id) {
		// TODO Auto-generated method stub
		
		UserEntity user = userRepository.findById(id).get();
		UserDTO userDTO = modelMapper.map(user, UserDTO.class);
		return userDTO;
	}

	@Override
	public List<UserDTO> findAll() {
		return null;
	}

	@Override
	public UserDTO add(UserDTO userDTO) {
		
		
		UserEntity user = userDTOToUser.convert(userDTO);
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		
		
		return userToUserDTO.convert(userRepository.save(user));
	}

	@Override
	public void deleteById(Integer id) {

	}

	@Override
	public UserDTO update(UserDTO object) {
		return null;
	}

	@Override
	public UserDTO findByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<UserEntity> userOptional = userRepository.findByEmail(email);
		if(userOptional.isEmpty()) {
			return null;
		}
		else {
			return userToUserDTO.convert(userOptional.get());
		}
	}


	public UserDTO edit(UserDTOEdit userDTOEdit ) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		//napravio bi proveru ako je username razlicit od praznog stringa i veci od 7 karaktera da setuje taj username, isto tako i za ime prezime i te gluposti
		Optional<UserEntity> userOptional = userRepository.findByEmail(userDetails.getUsername());
		//UserEntity user = userRepository.findByEmail(userDetails.getUsername()).get();   // jel moze ovde da se setuje samo posle zbog onog lifecycle u hibernate ?
		
		// moram hashovati old password da  bi bio equal sa passwordom iz baze.... ali ne znam kako
		
		UserEntity user = userOptional.get();
		System.out.println(user.getEmail());
	System.out.println(passwordEncoder.encode(userDTOEdit.getOldPassword()).equals(user.getPassword()));
	System.out.println(userDTOEdit.getOldPassword());
	System.out.println(passwordEncoder.encode(userDTOEdit.getOldPassword()));
		if(passwordEncoder.encode(userDTOEdit.getOldPassword()).equals(user.getPassword())) {      // u suprotnom baciti neki exception da se stari password ne poklapa
			
			if(userDTOEdit.getNewPassword().equals(userDTOEdit.getNewPassword2())) {    // u suprotnom baciti neki exception da passwordi nisu identicni, al ovo moze da se radi na frontendu provera
				  
				user.setPassword(passwordEncoder.encode(userDTOEdit.getNewPassword()));
				
				return userToUserDTO.convert(userRepository.save(user)); 
				
			}
			
			
		}
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

}
