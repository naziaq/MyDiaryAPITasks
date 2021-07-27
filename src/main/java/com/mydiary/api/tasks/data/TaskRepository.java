package com.mydiary.api.tasks.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TaskRepository extends JpaRepository<Task, String> {
	
	@Query(value = "SELECT * FROM tasks WHERE MATCH (task, description) AGAINST (?1)", nativeQuery = true)
	List<Task> searchTask(String keyword);
	
	List<Task> findByUserId(String userId);
}
