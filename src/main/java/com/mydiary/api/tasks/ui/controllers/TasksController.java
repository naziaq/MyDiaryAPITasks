package com.mydiary.api.tasks.ui.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mydiary.api.tasks.data.Task;
import com.mydiary.api.tasks.service.TasksService;

@RestController
//@RequestMapping("/tasks")
public class TasksController {

	@Autowired
	private Environment env;

	@Autowired
	TasksService tasksService;
	
	@GetMapping("/tasks/status/check")
	public String status() {

		return "My Task microservice is working on port " + env.getProperty("local.server.port");
	}

	// input - details of task
	// output - CREATED & Return the created URI
	// HATEOAS
	@PostMapping(path = "/users/{userId}/tasks", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> createTask(@PathVariable String userId, @Valid @RequestBody Task taskDetails) {

		taskDetails.setUserId(userId);
		Task createdTask = tasksService.createTask(taskDetails);
		URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTask.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	
	@GetMapping(path = "/tasks/{taskId}", produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Task> getTaskById(@PathVariable String taskId){
		
		Task task = tasksService.getTaskById(taskId);
		
		return ResponseEntity.status(HttpStatus.OK).body(task);
	}
	
	@PutMapping(path = "/tasks/{taskId}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Task> updateTask(@PathVariable String taskId, @RequestBody Task taskDetails){
		
		taskDetails.setId(taskId);
		Task updatedTask = tasksService.updateTask(taskDetails);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedTask);
	}
	
	@DeleteMapping(path = "/tasks/{taskId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> deleteTask(@PathVariable String taskId){
		
		tasksService.deleteTask(taskId);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/users/{userId}/tasks", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Task>> getAllTasks(@PathVariable String userId){
		
		List<Task> tasksList = tasksService.getAllTasks(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(tasksList);
	}
	
	@GetMapping(path = "/tasks/search/{keyword}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Task>> searchTask(@PathVariable String keyword){
		
		List<Task> searchedTasks = tasksService.searchTask(keyword);
		
		return ResponseEntity.status(HttpStatus.OK).body(searchedTasks);
		
	}

}
