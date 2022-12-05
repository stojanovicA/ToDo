package com.example.ToDo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.ToDo.dto.TaskDTO;
import com.example.ToDo.service.TaskService;

@RestController
@RequestMapping("tasks")
public class TaskController{
	
	@Autowired
	private TaskService taskService;
	
	
	//dopuniti ovu metodu da uzima query parametar search

	// i neka vraca sve taskove koji u naslovu sadrze taj search


	// /tasks - GET

	// /tasks?search=skola




	
	@GetMapping
	public ResponseEntity<List<TaskDTO>> get(@AuthenticationPrincipal UserDetails userDetails, @RequestParam String search){
		List<TaskDTO> tasks = taskService.findByUserAndTitle(userDetails.getUsername(), search);
		return new ResponseEntity<List<TaskDTO>>(tasks, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<TaskDTO> post(@RequestBody TaskDTO taskDTO) {
		TaskDTO savedTaskDTO = taskService.add(taskDTO);
		return new ResponseEntity<TaskDTO>(savedTaskDTO, HttpStatus.CREATED);
			
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Integer> delete (@PathVariable int id) {

		
	
		taskService.deleteById(id);
		//ResponseEntity.ok(id);	
		return new ResponseEntity<Integer>(id, HttpStatus.OK); 	
		
	}
	
	@PutMapping("{id}")
	public ResponseEntity<TaskDTO> updateTask(@PathVariable int id, @RequestBody TaskDTO taskDTO){
		 taskDTO.setId(id);
		return new ResponseEntity<TaskDTO>(taskService.update(taskDTO),HttpStatus.OK);
		
	}
	
	
	@GetMapping("{id}")
	public ResponseEntity<TaskDTO>getOne( @PathVariable int id){
				
			
		
		return new ResponseEntity<TaskDTO>(taskService.findById(id),HttpStatus.OK);
		
		
		
	}



	

	
	
	
	

}
