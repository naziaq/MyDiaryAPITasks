package com.mydiary.api.tasks.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(nullable = false, unique = true)
	private String id;
	
	@Size(min = 2, max = 50, message = "Task should be at least 2 characters and should not exceed 50 characters.")
	@NotNull(message = "Task cannot be null.")
	@Column(nullable = false, unique = true, length = 50)
	private String task;
	
	@Size(min = 2, max = 150, message = "Description should be at least 2 characters and should not exceed 150 characters.")
	@NotNull(message = "Description cannot be null.")
	@Column(nullable = false, length = 150)
	private String description;
	
	@Column(nullable = false)
	private String userId;
	
	//private List<String> categories;
	
	@Column
	private boolean isStarted;
	
	@Column
	private boolean isCompleted;
	
	@Column
	private String remarks;
	
	@Future(message = "Target date should be in future.")
	@Column
	private Date targetDate;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}

	public boolean isCompleted() {
		return isCompleted;
	}

	public void setCompleted(boolean isCompleted) {
		this.isCompleted = isCompleted;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}
	
	//private List<String> proficiencyLevel;

}
