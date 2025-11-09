package com.example.todo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class TaskEntity {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String tasks;

	    private LocalDateTime time;

	    public TaskEntity() {}

	    public TaskEntity(String tasks, LocalDateTime time) {
	        this.tasks = tasks;
	        this.time = time;
	    }

	    // Getters and Setters
	    public Long getId() { return id; }
	    public void setId(Long id) { this.id = id; }

	    public String getTasks() { return tasks; }
	    public void setTasks(String tasks) { this.tasks = tasks; }

	    public LocalDateTime getTime() { return time; }
	    public void setTime(LocalDateTime time) { this.time = time; }
}
