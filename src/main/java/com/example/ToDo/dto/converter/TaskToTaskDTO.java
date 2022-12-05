package com.example.ToDo.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.example.ToDo.dto.TaskDTO;
import com.example.ToDo.entity.Task;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;


@Component
public class TaskToTaskDTO implements Converter<Task, TaskDTO> {
	
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public TaskDTO convert(Task source) {
		return modelMapper.map(source, TaskDTO.class);
		
	}
	
	public List<TaskDTO> convert(List<Task> tasks){
		List<TaskDTO> tasksDTO = new ArrayList<>();
		for(Task task : tasks) {
			tasksDTO.add(convert(task));
		}
		return tasksDTO;
	}



}
