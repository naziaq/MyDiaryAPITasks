package com.mydiary.api.tasks.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mydiary.api.tasks.data.Task;
import com.mydiary.api.tasks.data.TaskRepository;

@Service
public class TaskServiceImpl implements TasksService {

	@Autowired
	TaskRepository taskRepository;
	
	
	@Override
	public Task createTask(Task taskDetails) {

		taskDetails.setId(UUID.randomUUID().toString());
		Task createdTask =  taskRepository.save(taskDetails);
		
		return createdTask;
	}

	@Override
	public Task getTaskById(String taskId) {
		
		Optional<Task> taskOptional =  taskRepository.findById(taskId);
		Task task = taskOptional.get();
		
		return task;
	}

	@Override
	public Task updateTask(Task taskDetails) {
		
		Task updatedTask =  taskRepository.save(taskDetails);
		
		return updatedTask;
	}

	@Override
	public void deleteTask(String taskId) {
		taskRepository.deleteById(taskId);
	}

	@Override
	public List<Task> getAllTasks(String userId) {
		
		List<Task> taskList = taskRepository.findByUserId(userId);

		return taskList;
	}

	@Override
	public List<Task> searchTask(String keyword) {
		
		List<Task> searchedTasks =  taskRepository.searchTask(keyword);
		
		return searchedTasks;
	}

}
