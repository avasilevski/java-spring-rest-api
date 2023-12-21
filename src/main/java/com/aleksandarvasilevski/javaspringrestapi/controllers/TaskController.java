package com.aleksandarvasilevski.javaspringrestapi.controllers;

import com.aleksandarvasilevski.javaspringrestapi.exceptions.TaskException;
import com.aleksandarvasilevski.javaspringrestapi.models.Task;
import com.aleksandarvasilevski.javaspringrestapi.services.TaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }
    @GetMapping()
    public List<Task> getAllTasks(){
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
        try {
            Task task = taskService.getTaskById(id);
            return ResponseEntity.ok(task);
        } catch (TaskException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody Task newTaskRequest, UriComponentsBuilder ucb) {
        Task savedTask = taskService.save(newTaskRequest);
        URI locationOfNewTask = ucb.path("/tasks/{id}").buildAndExpand(savedTask.getId()).toUri();
        return ResponseEntity.created(locationOfNewTask).build();
    }
}
