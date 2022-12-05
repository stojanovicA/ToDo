package com.example.ToDo.service.implementation;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.ToDo.dto.TaskDTO;
import com.example.ToDo.dto.converter.TaskDTOToTask;
import com.example.ToDo.dto.converter.TaskToTaskDTO;
import com.example.ToDo.entity.Task;
import com.example.ToDo.entity.UserEntity;
import com.example.ToDo.repository.TaskRepository;
import com.example.ToDo.repository.UserRepository;
import com.example.ToDo.service.TaskService;


@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	TaskRepository taskRepository;
	
	@Autowired
	TaskToTaskDTO taskToTaskDTO;
	
	@Autowired
	TaskDTOToTask taskDTOToTask;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public List<TaskDTO> findByUser_Email(String email) {
		
		List<Task> tasks = taskRepository.findByUser_Email(email);
			
		
		return taskToTaskDTO.convert(tasks);
	}

	@Override
	public List<TaskDTO> findByUserAndTitle(String email, String title) {
		return taskToTaskDTO.convert(taskRepository.findByUser_EmailAndTitleContainingIgnoreCase(email,title));
	}

	@Override
	public TaskDTO add(TaskDTO taskDTO) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		// TODO Auto-generated method stub
		Task task = taskDTOToTask.convert(taskDTO);
		UserEntity user = userRepository.findByEmail(userDetails.getUsername()).get();
		task.setUser(user);
		task.setCreationDateTime(LocalDateTime.now());
		
		return taskToTaskDTO.convert(taskRepository.save(task));
	}

	




	@Override
	public TaskDTO findById(Integer id) {
		return taskToTaskDTO.convert(taskRepository.findById(id).get());
	}

	@Override
	public List<TaskDTO> findAll() {
		return taskToTaskDTO.convert(taskRepository.findAll());
	}



	@Override
	public void deleteById(Integer id) {
		 taskRepository.deleteById(id);
	}

	@Override
	public TaskDTO update(TaskDTO taskDTO) {
		
		Task task = taskRepository.findById(taskDTO.getId()).get();
		task.setFinished(taskDTO.isFinished());
		
		task.setDescription(taskDTO.getDescription());
		
		task.setTitle(taskDTO.getTitle());
		
		
		
		// TODO Auto-generated method stub
		return taskToTaskDTO.convert(taskRepository.save(task));
	}
	}


