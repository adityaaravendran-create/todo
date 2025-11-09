package com.example.todo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.todo.entity.TaskEntity;
import com.example.todo.repository.TaskRepository;

import jakarta.validation.Valid;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "http://localhost:4200") // Allow Angular CORS
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    // Get all tasks
    @GetMapping
    public ResponseEntity<List<TaskEntity>> getAllTasks() {
        List<TaskEntity> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    // Get a single task by ID
    @GetMapping("/{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Long id) {
        return taskRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Add a new task
    @PostMapping
    public ResponseEntity<TaskEntity> addTask(@Valid @RequestBody TaskEntity task) {
        if (task.getTime() == null) {
            task.setTime(LocalDateTime.now()); // Default timestamp if not provided
        }
        TaskEntity savedTask = taskRepository.save(task);
        return ResponseEntity.status(201).body(savedTask);
    }

    // Update a task
    @PutMapping("/{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable Long id, @Valid @RequestBody TaskEntity updatedTask) {
        return taskRepository.findById(id).map(task -> {
            task.setTasks(updatedTask.getTasks());
            task.setTime(updatedTask.getTime() != null ? updatedTask.getTime() : task.getTime());
            TaskEntity savedTask = taskRepository.save(task);
            return ResponseEntity.ok(savedTask);
        }).orElseGet(() -> {
            updatedTask.setId(id);
            TaskEntity savedTask = taskRepository.save(updatedTask);
            return ResponseEntity.status(201).body(savedTask);
        });
    }

    // Delete a task
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (!taskRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        taskRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
