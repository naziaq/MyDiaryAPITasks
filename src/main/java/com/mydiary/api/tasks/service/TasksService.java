package com.mydiary.api.tasks.service;

import java.util.List;

import com.mydiary.api.tasks.data.Task;

public interface TasksService {
	
	Task createTask(Task taskDetails);
	
	Task getTaskById(String taskId);
	
	Task updateTask(Task taskDetails);
	
	void deleteTask(String taskId);
	
	List<Task> getAllTasks(String userId);
	
	List<Task> searchTask(String keyword);

}
