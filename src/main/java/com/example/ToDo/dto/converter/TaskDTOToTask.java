package com.example.ToDo.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.ToDo.dto.TaskDTO;
import com.example.ToDo.entity.Task;

@Component
public class TaskDTOToTask implements Converter<TaskDTO, Task>{
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Task convert(TaskDTO source) {
		// TODO Auto-generated method stub
		return modelMapper.map(source, Task.class);
	}

}
