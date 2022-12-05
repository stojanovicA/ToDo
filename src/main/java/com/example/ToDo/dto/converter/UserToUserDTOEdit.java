package com.example.ToDo.dto.converter;

import org.springframework.stereotype.Component;

import com.example.ToDo.dto.UserDTOEdit;
import com.example.ToDo.entity.UserEntity;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

@Component
public class UserToUserDTOEdit  implements Converter<UserEntity,UserDTOEdit>{
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDTOEdit convert(UserEntity source) {
		// TODO Auto-generated method stub
		return modelMapper.map(source, UserDTOEdit.class) ;
	}

}
