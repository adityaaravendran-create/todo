package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.todo.entity.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> { }

